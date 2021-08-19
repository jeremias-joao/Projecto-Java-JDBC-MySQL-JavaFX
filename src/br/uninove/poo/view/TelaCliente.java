package br.uninove.poo.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import br.uninove.poo.persistencia.Cliente;
import br.uninove.poo.persistencia.ClienteDAO;
 
public class TelaCliente extends JFrame implements ActionListener {
 
    JButton btnSalvar;
    JLabel lblId, lblNome, lblTelefone, lblSexo, lblRenda;
    JTextField txtNome;
    JFormattedTextField txtTelefone, txtId, txtRenda;
    MaskFormatter mskTelefone, mskId;
    ButtonGroup gruSexo;
    JRadioButton rdoMasculino, rdoFeminino;
 
    public TelaCliente() {
        setTitle("Cadastro");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(400, 200, 600, 280);
        lblId = new JLabel("ID:");
        lblId.setBounds(10, 10, 50, 30);
        add(lblId);
        txtId = new JFormattedTextField();
        txtId.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#"))));
        txtId.setBounds(10, 40, 150, 30);
        txtId.requestFocus();
        add(txtId);
        lblNome = new JLabel("Nome:");
        lblNome.setBounds(170, 10, 50, 30);
        add(lblNome);
 
        txtNome = new JTextField(40);
        txtNome.setBounds(170, 40, 400, 30);
        add(txtNome);
 
        lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(10, 80, 150, 30);
        add(lblTelefone);
        try {
            mskTelefone = new MaskFormatter("(##)####-####");
            mskTelefone.setPlaceholderCharacter('_');
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        txtTelefone = new JFormattedTextField(mskTelefone);
        txtTelefone.setBounds(10, 110, 100, 30);
        add(txtTelefone);
 
        lblSexo = new JLabel("Sexo:");
        lblSexo.setBounds(170, 80, 150, 30);
        add(lblSexo);
 
        rdoMasculino = new JRadioButton("Masculino");
        rdoMasculino.setBounds(170, 110, 100, 30);
        rdoFeminino = new JRadioButton("Feminino");
        rdoFeminino.setBounds(300, 110, 100, 30);
        gruSexo = new ButtonGroup();
        gruSexo.add(rdoMasculino);
        gruSexo.add(rdoFeminino);
        add(rdoMasculino);
        add(rdoFeminino);
 
        lblRenda = new JLabel("Renda:");
        lblRenda.setBounds(450, 80, 150, 30);
        add(lblRenda);
 
        txtRenda = new JFormattedTextField();
        txtRenda.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0.00"))));
        txtRenda.setBounds(450, 110, 100, 30);
        txtRenda.setToolTipText("Utilize vírgula para separar as casas decimais");
        add(txtRenda);
 
        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(10, 170, 150, 30);
        btnSalvar.addActionListener(this);
        add(btnSalvar);
    }
 
    public static void main(String args[]) {
        TelaCliente tela = new TelaCliente();
        tela.setVisible(true);
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        String mensagem;
        if (e.getSource() == btnSalvar) {
            int id;
            double renda;
            String nome, telefone, sexo;
            id = Integer.parseInt(txtId.getText());
            nome = txtNome.getText();
            telefone = txtTelefone.getText();
            if (rdoMasculino.isSelected()) {
                sexo = rdoMasculino.getText();
            } else if (rdoFeminino.isSelected()) {
                sexo = rdoFeminino.getText();
            } else {
                sexo = "";
            }
            renda = Double.parseDouble(txtRenda.getText().replace(",", "."));
            Cliente cli = new Cliente(id, nome, telefone, sexo, renda);
            ClienteDAO dao = new ClienteDAO();
            int r = dao.conectar();
            if (r == 2) {
                mensagem = "Driver de conexão não foi encontrado";
            } else if (r == 3) {
                mensagem = "Os dados de conexão com o banco de dados estão incorretos";
            } else {
                int x = dao.salvar(cli);
                if (x == 1) {
                    mensagem = "Dados gravados com sucesso";
                    limparCampos();
                } else if (x == 2) {
                    mensagem = "Você está tentando cadastrar um ID que já existe";
                } else {
                    mensagem = "Dados gravados com sucesso";
                }
                dao.desconectar();
            }
            JOptionPane.showMessageDialog(null, mensagem);
        }
    }
 
    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtTelefone.setText("");
        gruSexo.clearSelection();
        txtRenda.setText("");
        txtId.requestFocus();
    }
}