import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JogoDaVelha extends JFrame {

    private char currentPlayer = 'X';
    private JButton[][] cells = new JButton[3][3];
    private int vitoriasJogadorX = 0;
    private int vitoriasJogadorO = 0;
    private JLabel statusLabel;

    public JogoDaVelha() {
        setTitle("Jogo da Velha");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel para o tabuleiro
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3, 5, 5));

        // Criação e configuração dos botões do tabuleiro
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final JButton cell = new JButton();
                cell.setPreferredSize(new Dimension(100, 100));
                cell.setFont(new Font("Arial", Font.PLAIN, 40));
                cell.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (cell.getText().isEmpty()) {
                            cell.setText(String.valueOf(currentPlayer));
                            if (verificarVitoria()) {
                                JOptionPane.showMessageDialog(null, "Jogador " + currentPlayer + " ganhou!");
                                if (currentPlayer == 'X') {
                                    vitoriasJogadorX++;
                                } else {
                                    vitoriasJogadorO++;
                                }
                                atualizarStatusLabel();
                                reiniciarJogo();
                            } else if (verificarEmpate()) {
                                JOptionPane.showMessageDialog(null, "Empate!");
                                reiniciarJogo();
                            } else {
                                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                            }
                        }
                    }
                });
                cells[i][j] = cell;
                boardPanel.add(cell); // Adiciona o botão ao painel do tabuleiro
            }
        }

        // Painel para o placar
        JPanel scorePanel = new JPanel(new GridLayout(2, 1));
        JLabel scoreLabel = new JLabel("Placar:");
        statusLabel = new JLabel("Jogador X: " + vitoriasJogadorX + " | Jogador O: " + vitoriasJogadorO);
        scorePanel.add(scoreLabel);
        scorePanel.add(statusLabel);

        // Botão para reiniciar o jogo e o placar
        JButton restartButton = new JButton("Reiniciar");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarJogo(); // Reinicia o jogo
                vitoriasJogadorX = 0; // Reinicia o placar do jogador X
                vitoriasJogadorO = 0; // Reinicia o placar do jogador O
                atualizarStatusLabel(); // Atualiza o placar na interface
            }
        });

        // Botão para sair do jogo
        JButton exitButton = new JButton("Sair");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Fecha o aplicativo
            }
        });

        // Painel para os botões de reiniciar e sair
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(restartButton);
        buttonPanel.add(exitButton);

        // Adicionando componentes à janela principal
        add(boardPanel, BorderLayout.CENTER); // Adiciona o painel do tabuleiro ao centro
        add(scorePanel, BorderLayout.NORTH); // Adiciona o painel do placar acima do tabuleiro
        add(buttonPanel, BorderLayout.SOUTH); // Adiciona o painel dos botões abaixo do tabuleiro

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean verificarVitoria() {
        for (int i = 0; i < 3; i++) {
            if (cells[i][0].getText().equals(cells[i][1].getText()) &&
                    cells[i][0].getText().equals(cells[i][2].getText()) &&
                    !cells[i][0].getText().isEmpty()) {
                return true;
            }
            if (cells[0][i].getText().equals(cells[1][i].getText()) &&
                    cells[0][i].getText().equals(cells[2][i].getText()) &&
                    !cells[0][i].getText().isEmpty()) {
                return true;
            }
        }
        if (cells[0][0].getText().equals(cells[1][1].getText()) &&
                cells[0][0].getText().equals(cells[2][2].getText()) &&
                !cells[0][0].getText().isEmpty()) {
            return true;
        }
        if (cells[0][2].getText().equals(cells[1][1].getText()) &&
                cells[0][2].getText().equals(cells[2][0].getText()) &&
                !cells[0][2].getText().isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean verificarEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void atualizarStatusLabel() {
        statusLabel.setText("Jogador X: " + vitoriasJogadorX + " | Jogador O: " + vitoriasJogadorO); // Atualiza o texto do placar
    }

    private void reiniciarJogo() {
        currentPlayer = 'X';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j].setText(""); // Limpa o texto de todos os botões do tabuleiro
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JogoDaVelha();
            }
        });
    }
}