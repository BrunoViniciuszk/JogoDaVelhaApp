package br.senai.sp.cotia.myapplication.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

import br.senai.sp.cotia.myapplication.R;
import br.senai.sp.cotia.myapplication.databinding.FragmentJogoBinding;


public class FragmentJogo extends Fragment {

    // variavel para acessar os elementos da view
    private FragmentJogoBinding binding;
    // array de botao
    private Button[] botoes;
    // variavel que representa o tabuleiro
    private String[][] tabuleiro;
    // variavel para os simbolos
    private String simbJog1, simbJog2, simbolo;
    // variavel random para sortear quem começa
    private Random random;
    // variavel da joagada
    private int numeroJogadas=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Instancia o binding
        binding = FragmentJogoBinding.inflate(inflater, container, false);

        // instancia o vetor
        botoes = new Button[9];
        // agrupa os botoes no vetor
        botoes[0] = binding.bt00;
        botoes[1] = binding.bt01;
        botoes[2] = binding.bt02;
        botoes[3] = binding.bt10;
        botoes[4] = binding.bt11;
        botoes[5] = binding.bt12;
        botoes[6] = binding.bt20;
        botoes[7] = binding.bt21;
        botoes[8] = binding.bt22;

        // associa o listener aos botoes
        for(Button bt : botoes) {
            bt.setOnClickListener(listenerBotoes);
        }

        // inicializa o tabuleiro
        tabuleiro = new String[3][3];

        // preencher o tabuleiro com string vazia
        for(String[] vetor : tabuleiro){
            Arrays.fill(vetor, "");
        }

        // instancia o Random
        random = new Random();

        //define os simbolos dos jogadores
        simbJog1 = "X";
        simbJog2 = "O";

        // sorteia quem inicia o jogo
        sorteia();

        // atualizar vez
        atualizaVez();

        // retorna a view do Fragment
        return  binding.getRoot();
    }

    private void sorteia() {
        // caso random gere um valor verdadeiro, jogador 1 começa
        // caso contrario jogador 2 começa
        if(random.nextBoolean()) {
            simbolo = simbJog1;
        }else {
            simbolo = simbJog2;
        }
    }

    private void atualizaVez() {
        if(simbolo.equals(simbJog1)) {
            binding.jogador1.setBackgroundColor(getResources().getColor(R.color.teal_900));
            binding.jogador2.setBackgroundColor(getResources().getColor(R.color.teal_700));
        }else {
            binding.jogador2.setBackgroundColor(getResources().getColor(R.color.teal_900));
            binding.jogador1.setBackgroundColor(getResources().getColor(R.color.teal_700));
        }
    }

    private void resetar() {
            for(String[] vetor : tabuleiro){
                Arrays.fill(vetor, "");
            }
            for(Button botao : botoes) {
                botao.setClickable(true);
                botao.setBackgroundColor(getResources().getColor(R.color.teal_700));
                botao.setText("");
            }

            // sorteia quem ira iniciar o próximo jogo
            sorteia();
            // atualiza a vez no placar
            atualizaVez();
            // zerar o número de jogadas
            numeroJogadas = 0;
    }

    private boolean venceu() {
        //verifica se venceu nas linhas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0].equals(simbolo) && tabuleiro[i][1].equals(simbolo) && tabuleiro[i][2].equals(simbolo)) {
                return true;
            }
        }
        //verifica se venceu na coluna
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[0][i].equals(simbolo) && tabuleiro[1][i].equals(simbolo) && tabuleiro[2][i].equals(simbolo)) {
                return true;
            }
        }
        //verifica se ganhaou na diagonal
        if (tabuleiro[0][0].equals(simbolo) && tabuleiro[1][1].equals(simbolo) && tabuleiro[2][2].equals(simbolo)) {
            return true;
        }

        if (tabuleiro[0][2].equals(simbolo) && tabuleiro[1][1].equals(simbolo) && tabuleiro[2][0].equals(simbolo)) {
            return true;
        }
        return false;
    }


    private View.OnClickListener listenerBotoes = btPress-> {
        // incrementa as jogadas
        numeroJogadas++;
        // pega o nome do botao
        String nomeBotao = getContext().getResources().getResourceName(btPress.getId());
        // extrai os dois ultimos caracteres do nome botao
        String posicao = nomeBotao.substring(nomeBotao.length()-2);
        // extrai a posicao em linha e coluna
        int linha = Character.getNumericValue(posicao.charAt(0));
        int coluna = Character.getNumericValue(posicao.charAt(1));

        // marca no tabuleiro o simbolo que foi jogado
        tabuleiro[linha][coluna] = simbolo;
        // faz o casting de View para Button
        Button botao = (Button) btPress;
        // trocar o texto do botao que foi clicado
        botao.setText(simbolo);
        // desabilitar botao
        botao.setClickable(false);

        // troca o background do botao
        botao.setBackgroundColor(Color.WHITE);
        botao.setTextColor(Color.BLACK);

        // verificar se ganhou
        if(numeroJogadas >= 5 && venceu()) {
            // exibe um Toast  informando que o jogador venceu
            Toast.makeText(getContext(), R.string.vencedor, Toast.LENGTH_LONG).show();
            // resetar tabuleiro
            resetar();
        }else if(numeroJogadas == 9) {
            // exibe um Toast  informando que o jogador venceu
            Toast.makeText(getContext(), R.string.velha, Toast.LENGTH_SHORT).show();
            // resetar tabuleiro
            resetar();
        }else {
            // inverter a vez
            simbolo = simbolo.equals(simbJog1) ? simbJog2 : simbJog1;

            // atualiza vez
            atualizaVez();
        }






    };
}