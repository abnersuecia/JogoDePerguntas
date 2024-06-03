import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JogoDePerguntas extends JFrame {
    private JLabel perguntaLabel;
    private JButton[] opcoesButton;
    private JLabel pontuacaoLabel;
    private int pontuacao = 0;
    private int perguntaAtual = 0;

    private String[][] perguntas = {
            {"Qual é a capital da França?", "Paris", "Berlim", "Londres", "Roma", "Paris"},
            {"Quem pintou a Mona Lisa?", "Pablo Picasso", "Leonardo da Vinci", "Vincent van Gogh", "Michelangelo", "Leonardo da Vinci"},
            {"Qual é o maior planeta do sistema solar?", "Terra", "Vênus", "Júpiter", "Marte", "Júpiter"},
            {"Quem escreveu 'Dom Quixote'?", "Miguel de Cervantes", "William Shakespeare", "Friedrich Nietzsche", "Franz Kafka", "Miguel de Cervantes"},
            {"Qual é a maior cordilheira do mundo?", "Andes", "Himalaias", "Montanhas Rochosas", "Alpes", "Himalaias"},
            {"Quem foi o primeiro presidente dos Estados Unidos?", "George Washington", "Thomas Jefferson", "Abraham Lincoln", "John F. Kennedy", "George Washington"},
            {"Quantos elementos químicos existem na tabela periódica?", "118", "92", "103", "110", "118"},
            {"Qual é o país com a maior área territorial do mundo?", "Rússia", "Canadá", "China", "Brasil", "Rússia"},
            {"Quem escreveu 'Romeu e Julieta'?", "William Shakespeare", "Charles Dickens", "Jane Austen", "Mark Twain", "William Shakespeare"},
            {"Em que ano ocorreu a Primeira Guerra Mundial?", "1914", "1939", "1945", "1918", "1914"}
    };

    public JogoDePerguntas() {
        setTitle("Jogo de Perguntas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        perguntaLabel = new JLabel();
        perguntaLabel.setFont(new Font("Arial", Font.BOLD, 20));
        perguntaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(perguntaLabel, BorderLayout.NORTH);

        JPanel opcoesPanel = new JPanel(new GridLayout(2, 2));
        opcoesButton = new JButton[4];
        for (int i = 0; i < 4; i++) {
            opcoesButton[i] = new JButton();
            opcoesButton[i].setFont(new Font("Arial", Font.BOLD, 20));
            opcoesButton[i].addActionListener(new OpcaoListener());
            opcoesPanel.add(opcoesButton[i]);
        }
        add(opcoesPanel, BorderLayout.CENTER);

        pontuacaoLabel = new JLabel("Pontuação: 0");
        pontuacaoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(pontuacaoLabel, BorderLayout.SOUTH);
        pontuacaoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        mostrarPergunta();
    }

    private void mostrarPergunta() {
        perguntaLabel.setText(perguntas[perguntaAtual][0]);
        for (int i = 0; i < 4; i++) {
            opcoesButton[i].setText(perguntas[perguntaAtual][i + 1]);
        }
    }

    private class OpcaoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton botaoClicado = (JButton) e.getSource();
            String resposta = botaoClicado.getText();
            String respostaCorreta = perguntas[perguntaAtual][5];
            if (resposta.equals(respostaCorreta)) {
                pontuacao++;
                JOptionPane.showMessageDialog(null, "Resposta correta!");
            } else {
                JOptionPane.showMessageDialog(null, "Resposta errada!");
            }
            pontuacaoLabel.setText("Pontuação: " + pontuacao);
            perguntaAtual++;
            if (perguntaAtual < perguntas.length) {
                mostrarPergunta();
            } else {
                JOptionPane.showMessageDialog(null, "Fim do jogo! Sua pontuação é: " + pontuacao);
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }
}

class MenuPrincipal extends JFrame {
    public MenuPrincipal() {
        setTitle("Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Menu Principal", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        add(Box.createRigidArea(new Dimension(0, 20)));

        JButton btnStartGame = new JButton("Iniciar Jogo de perguntas");
        btnStartGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnStartGame.setFont(new Font("Arial", Font.BOLD, 18));
        btnStartGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new JogoDePerguntas().setVisible(true);
                dispose();
            }
        });
        add(btnStartGame);

        JButton btnStartGameVelha = new JButton("Iniciar Jogo da Velha");
        btnStartGameVelha.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnStartGameVelha.setFont(new Font("Arial", Font.BOLD, 18));
        btnStartGameVelha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new JogoDaVelha().setVisible(true);
                dispose();
            }
        });
        add(btnStartGameVelha);

        JButton btnOption2 = new JButton("Desenvolvedores");
        btnOption2.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOption2.setFont(new Font("Arial", Font.BOLD, 18));
        btnOption2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Nathália, Beatriz, Pedro Carmo e Abner.");
            }
        });
        add(btnOption2);

        JButton btnOption3 = new JButton("Fotos de árvores");
        btnOption3.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOption3.setFont(new Font("Arial", Font.BOLD, 18));
        btnOption3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Carregar as imagens
                ImageIcon icon1 = new ImageIcon("arvore1.jpg");
                ImageIcon icon2 = new ImageIcon("arvore2.jpg");
                ImageIcon icon3 = new ImageIcon("arvore3.png");
                ImageIcon icon4 = new ImageIcon("arvore4.png");

                // Verificar se as imagens foram carregadas corretamente
                if (icon1.getIconWidth() == -1 || icon2.getIconWidth() == -1 || icon3.getIconWidth() == -1 || icon4.getIconWidth() == -1) {
                    JOptionPane.showMessageDialog(null, "Alguma imagem não foi encontrada ou é inválida.");
                } else {
                    // Criar um painel para exibir as imagens
                    JPanel panel = new JPanel(new GridLayout(2, 2));

                    // Definir o tamanho das imagens
                    int imageSize = 200; // Tamanho desejado para as imagens
                    Image image1 = icon1.getImage().getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
                    Image image2 = icon2.getImage().getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
                    Image image3 = icon3.getImage().getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
                    Image image4 = icon4.getImage().getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
                    icon1 = new ImageIcon(image1);
                    icon2 = new ImageIcon(image2);
                    icon3 = new ImageIcon(image3);
                    icon4 = new ImageIcon(image4);

                    // Adicionar as imagens ao painel
                    panel.add(new JLabel(icon1));
                    panel.add(new JLabel(icon2));
                    panel.add(new JLabel(icon3));
                    panel.add(new JLabel(icon4));

                    // Exibir as imagens em um JOptionPane
                    JOptionPane.showMessageDialog(null, panel, "Fotos de Árvores", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        add(btnOption3);
        add(btnOption3);
        add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnExit = new JButton("Sair");
        btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExit.setFont(new Font("Arial", Font.BOLD, 18));
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(btnExit);

        setVisible(true);
    }
}
