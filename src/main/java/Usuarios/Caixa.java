package Usuarios;

import TiposAtributos.*;

import javax.swing.*;

public class Caixa extends Usuario implements Administrador {

    public Caixa(String nome,
                 DataDeNascimento dataNascimento,
                 CPF cpf,
                 Endereco endereco,
                 Telefone telefone,
                 Email email,
                 String senha) {
        super(nome, dataNascimento, cpf, endereco, telefone, email, senha);
        userID = (id++) + "C";
        tipoUsuario = "Caixa";
    }

    @Override
    public void realizarSaque(Cliente origem, double valor) {
        origem.confirmarSaldo(valor);
        if (confirmaValor(valor)) {
            if (valor < 1000000 && origem.confirmaSenha()) {
                origem.sacar(valor);
                JOptionPane.showMessageDialog(null, "Saque concluído com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Senha incorreta, cancelando operação.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void realizarDeposito(Cliente destino, double valor) {
        if (confirmaValor(valor)) {
            destino.depositar(valor);
            JOptionPane.showMessageDialog(null, "Depósito concluído com sucesso.");
        }
    }

}