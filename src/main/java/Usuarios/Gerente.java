//Nome: Ian Felix Fernandes Matrícula: 202376007

package Usuarios;

import Investimentos.RendaFixa;
import Investimentos.RendaVariavel;
import Persistencias.PersistenciaRendaFixa;
import Persistencias.PersistenciaRendaVariavel;
import TiposAtributos.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Gerente extends Usuario implements Administrador {

    private static List<RendaFixa> listaRendaFixa = new ArrayList<>();
    private static List<RendaVariavel> listaRendaVariavel = new ArrayList<>();

    public Gerente(String nome,
                   DataDeNascimento dataNascimento,
                   CPF cpf,
                   Endereco endereco,
                   Telefone telefone,
                   Email email,
                   String senha) {
        super(nome, dataNascimento, cpf, endereco, telefone, email, senha);
        userID = id + "G";
        tipoUsuario = "Gerente";
        listaRendaFixa = new ArrayList<>();
    }

    public static List<RendaFixa> getListaRendaFixa() {
        return listaRendaFixa;
    }

    public static void carregarRendaFixa() {
        listaRendaFixa = PersistenciaRendaFixa.carregarRendaFixa();
    }

    public static void salvarRendaFixa() {
        PersistenciaRendaFixa.salvarRendaFixa(listaRendaFixa);
    }

    public static List<RendaVariavel> getListaRendaVariavel() {
        return listaRendaVariavel;
    }

    public static void carregarRendaVariavel() {
        listaRendaVariavel = PersistenciaRendaVariavel.carregarVariavel();
    }

    public static void salvarRendaVariavel() {
        PersistenciaRendaVariavel.salvarRendaVariavel(listaRendaVariavel);
    }

    @Override
    public void realizarSaque(Cliente origem, double valor) {
        origem.confirmarSaldo(valor);
        if (confirmaValor(valor)) {
            if (origem.confirmaSenha()) {
                origem.sacar(valor, "Saque: ");
                JOptionPane.showMessageDialog(null, "Saque concluído com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Senha incorreta, cancelando operação.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void realizarDeposito(Cliente destino, double valor) {
        if (confirmaValor(valor)) {
            destino.depositar(valor, "Depósito: ");
            JOptionPane.showMessageDialog(null, "Depósito concluído com sucesso.");
        }
    }

    public void cadastraRendaFixa(String nomeInvestimento, String resgateMin, String resgateMax, double rendimento) {
        Objects.requireNonNull(nomeInvestimento);
        Objects.requireNonNull(resgateMin);
        Objects.requireNonNull(resgateMax);
        if (listaRendaFixa.isEmpty()) {
            this.confirmaSenha();
            listaRendaFixa.add(new RendaFixa(nomeInvestimento, resgateMin, resgateMax, rendimento));
            JOptionPane.showMessageDialog(null, "Investimento criado com sucesso.");
        } else {
            for (RendaFixa investimento : listaRendaFixa) {
                if (!investimento.getNome().equals(nomeInvestimento)) {
                    if (this.confirmaSenha()) {
                        listaRendaFixa.add(new RendaFixa(nomeInvestimento, resgateMin, resgateMax, rendimento));
                        JOptionPane.showMessageDialog(null, "Investimento criado com sucesso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Senha incorreta ou o usuário cancelou a operação.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nome de investimento repetido", "Duplicado", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        salvarRendaFixa();
    }

}
