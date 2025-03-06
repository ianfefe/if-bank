package Usuarios;

import TiposAtributos.*;

import javax.swing.*;
import java.util.Objects;

public class Caixa extends Usuario implements Administrador {

    public Caixa(String nome,
                 DataDeNascimento dataNascimento,
                 CPF cpf,
                 Endereco endereco,
                 Telefone telefone,
                 Email email,
                 String senha) {
        super(nome, dataNascimento, cpf, endereco, telefone, email, senha);
        userID = id + "C";
        tipoUsuario = "Caixa";
    }

    @Override
    public void realizarSaque(Cliente origem, double valor) {
        Objects.requireNonNull(origem, "Conta de origem não encontrada.");
        origem.confirmarSaldo(valor);
        if (confirmaValor(valor)) {
            if (valor < 1000000) {
                if (origem.confirmaSenha()) {
                    origem.sacar(valor, "Saque: ");
                    JOptionPane.showMessageDialog(null, "Saque concluído com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(null, "Senha incorreta, cancelando operação.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Para realizar saques maiores que R$1.000.000,00, peça ajuda ao gerente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void realizarDeposito(Cliente destino, double valor) {
        Objects.requireNonNull(destino, "Conta destino não encontrada.");
        if (confirmaValor(valor)) {
            destino.depositar(valor, "Depósito: ");
            JOptionPane.showMessageDialog(null, "Depósito concluído com sucesso.");
        }
    }

}