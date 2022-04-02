package br.com.dio.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;

import br.com.dio.dominio.Conta;
import br.com.dio.dominio.Genero;
import br.com.dio.negocio.TransacaoHelper;
import br.com.dio.negocio.ValidacaoHelper;

public class BancoDigital {

    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        TransacaoHelper transacaoHelper = new TransacaoHelper();

        List<Conta> contas = new ArrayList<Conta>();

        String opcao = "";

        boolean sair = false;

        Scanner teclado = new Scanner(System.in);


        System.out.println("Seja bem vinde! ^_^ ");
        System.out.println("Por favor escolha a opção desejada. ;D ");


        try {
            do {
                System.out.println("Digite 0 para Logoff");
                System.out.println("Digite 1 para criação de conta ");
                System.out.println("Digite 2 para mostrar as contas");
                System.out.println("Digite 3 para exibir os seus dados pelo CPF informado ");
                System.out.println("Digite 4 para verificar saldo pelo CPF ");
                System.out.println("Digite 5 para solicitar Cartão de Crédito");
                System.out.println("Digite 6 para fazer uma saque ");
                System.out.println("Digite 7 para fazer um depósito");
                System.out.println("Digite 8 para fazer um PIX");

                System.out.print("Informe a opção: ");
                opcao = teclado.nextLine();

                switch (opcao) {
                    case "0":

                        System.out.println("O banco AF agradece a sua visita! ");
                        System.out.println("Encerrando. Aguarde um momento");

                        sair = true;

                        break;
                    case "1":
                        System.out.println("-> CRIAR CONTA");

                        Conta c1 = new Conta();
                        System.out.print("Informe o nome : ");
                        c1.getPessoa().setNome(teclado.nextLine());

                        System.out.print("Informe o CPF:  ");
                        c1.getPessoa().setCpf(teclado.nextLine());

                        System.out.print("Informe o genêro da pessoa (Masculino ou Feminino): ");
                        c1.getPessoa().setGenero(Genero.modificarStringToGenero(teclado.nextLine()));

                        System.out.print("Informe a data de nascimento da pessoa: ");
                        String dataNascimento = teclado.nextLine();

                        if(!dataNascimento.isEmpty()) {
                            c1.getPessoa().setDataNascimento(simpleDateFormat.parse(dataNascimento));
                        }

                        if(ValidacaoHelper.isPossivelCadastrarConta(c1)) {
                            contas.add(c1);

                            System.out.println("Conta criada com sucesso!");
                        }

                        break;
                    case "2":
                        System.out.println("-> LISTAR CONTAS");

                        if(!contas.isEmpty()) {
                            for (Conta conta : contas) {
                                System.out.println("Número:" + conta.getNumero() + "Agência: " + conta.getCodigo());
                            }
                        }

                        break;
                    case "3":
                        System.out.println("-> DADOS BANCÁRIOS");

                        System.out.print("Informe o CPF da pessoa: ");
                        String cpfDados = teclado.nextLine();

                        if(ValidacaoHelper.isContaExistente(contas, cpfDados)) {
                            for (Conta conta : contas) {
                                if(conta.getPessoa().getCpf().equals(cpfDados)) {
                                    transacaoHelper.exibirDadosBancarios(conta);
                                }
                            }
                        }

                        break;
                    case "4":
                        System.out.println("-> SALDO");

                        System.out.print("Informe o CPF da pessoa: ");
                        String cpfSaldo = teclado.nextLine();

                        if(ValidacaoHelper.isContaExistente(contas, cpfSaldo)) {
                            for (Conta conta : contas) {
                                if(conta.getPessoa().getCpf().equals(cpfSaldo)) {
                                    System.out.println("O saldo é de: " + conta.getSaldo());
                                }
                            }
                        }
                        break;
                    case "5":
                        System.out.println("-> SOLICITAR CARTÃO");

                        System.out.print("Informe o CPF da pessoa: ");
                        String cpfCartao = teclado.nextLine();

                        if(ValidacaoHelper.isContaExistente(contas, cpfCartao)) {
                            for (Conta conta : contas) {
                                if(conta.getPessoa().getCpf().equals(cpfCartao)) {
                                    transacaoHelper.solicitarCartao(conta);
                                    System.out.println("Cartão solicitado com sucesso! Aguarde até 15 dias uteis.");
                                }
                            }
                        }

                        break;
                    case "6":
                        System.out.println("-> SAQUE");

                        System.out.print("Informe o CPF da pessoa: ");
                        String cpfSacar = teclado.nextLine();

                        if(ValidacaoHelper.isContaExistente(contas, cpfSacar)) {
                            String valor;

                            System.out.print("Informe o valor do saque: ");
                            valor = teclado.nextLine();

                            for (Conta conta : contas) {
                                if(conta.getPessoa().getCpf().equals(cpfSacar)) {
                                    transacaoHelper.sacar(conta, Double.parseDouble(valor));
                                }
                            }
                        }

                        break;
                    case "7":
                        System.out.println("-> DEPÓSITO");

                        System.out.print("Informe o CPF da pessoa: ");
                        String cpfDeposito = teclado.nextLine();

                        if(ValidacaoHelper.isContaExistente(contas, cpfDeposito)) {
                            String valor;

                            System.out.print("Informe o valor do depósito: ");
                            valor = teclado.nextLine();

                            for (Conta conta : contas) {
                                if(conta.getPessoa().getCpf().equals(cpfDeposito)) {
                                    transacaoHelper.depositar(conta, Double.parseDouble(valor));
                                }
                            }
                        }

                        break;
                    case "8":
                        System.out.println("PIX");

                        System.out.print("Informe o seu CPF: ");
                        String cpfDepositante = teclado.nextLine();

                        System.out.print("Informe a chave PIX em formato de CPF da conta que receberá o dinheiro:  ");
                        String cpfRecebedor = teclado.nextLine();

                        if(ValidacaoHelper.isContaExistente(contas, cpfDepositante)
                                && ValidacaoHelper.isContaExistente(contas, cpfRecebedor)) {
                            Conta contaDepositante = null, contaRecebedor = null;
                            String valor;

                            System.out.print("Informe o valor do depósito: ");
                            valor = teclado.nextLine();

                            for (Conta conta : contas) {
                                if(conta.getPessoa().getCpf().equals(cpfDepositante)) {
                                    contaDepositante = conta;
                                }else if (conta.getPessoa().getCpf().equals(cpfRecebedor)){
                                    contaRecebedor = conta;
                                }
                            }

                            transacaoHelper.transferir(contaDepositante, contaRecebedor, Double.parseDouble(valor));
                        }

                        break;
                    default:
                        break;
                }
            }while(!sair);

        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            teclado.close();
        }
    }
}
