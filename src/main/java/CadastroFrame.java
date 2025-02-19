import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class CadastroFrame extends JFrame{
    private JTextField campoNome;
    private JPasswordField campoSenha;
    private JPasswordField campoSenhaConfirma;
    private JLabel senha;
    private JLabel senhaConfirma;
    private JTextField campoEmail;
    private JTextField campoTelefone;
    private JTextField campoCelular;
    private JLabel email;
    private JLabel telefone;
    private JLabel celular;
    private JTextField textField3;
    private JTextField textField4;
    private JPanel endereco;
    private JPanel formasContato;
    private JPanel confirmacaoSenha;
    private JPanel cadastroPanel;
    private JTextField campoComplemento;
    private JLabel complemento;
    private JButton confirmarCadastroButton;
    private JLabel nomeCompleto;
    private JLabel dataDeNascimentoLabel;
    private JTextField campoDataNascimento;
    private JLabel CPFLabel;
    private JTextField campoCPF;

    public CadastroFrame() {

        setSize(900,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(cadastroPanel);

        confirmarCadastroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(Arrays.equals(campoSenha.getPassword(), campoSenhaConfirma.getPassword())){
                    String nome = campoNome.getText();
                    String endereco = "default";
                    String telefone = campoTelefone.getText();
                    String email = campoEmail.getText();
                    int senha = 1234;

                    new Sistema().criarUsuario(nome,endereco,telefone,email,senha);

                    JOptionPane.showMessageDialog(null,"Usuário criado " +
                            "Nome: " + nome + " Endereco: " + endereco + " Telefone: " + telefone + "Email: " + email + " Senha: " + senha);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CadastroFrame teste = new CadastroFrame();
            teste.setContentPane(teste.cadastroPanel);
            teste.setSize(900,600);
            teste.setLocationRelativeTo(null);
            teste.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            teste.setVisible(true);
        });

    }

}
