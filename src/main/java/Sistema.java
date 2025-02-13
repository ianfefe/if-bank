public class Sistema {



    void criarUsuario(String nome, Endereco endereco, Telefone telefone, Email email, int senha){
        switch (TipoDeUsuario){
            case TipoDeUsuario.CAIXA:
                Caixa id = new Caixa(nome, endereco, telefone, email, senha);
            case TipoDeUsuario.CLIENTE:
                Cliente id2 = new Cliente(nome, endereco, telefone, email, senha);
            case TipoDeUsuario.CAIXA:
                Gerente id3 = new Gerente(nome, endereco, telefone, email, senha);
        }
    };

    void removerUsuario(Usuario usuario){

    };

    void editarUsuario(Usuario usuario){

    };
}
