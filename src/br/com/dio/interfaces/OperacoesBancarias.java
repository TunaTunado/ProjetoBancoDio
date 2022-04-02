package br.com.dio.interfaces;

import br.com.dio.dominio.Conta;

import java.text.ParseException;

public interface OperacoesBancarias {


    public void sacar(Conta conta, double valor);


    public void depositar(Conta conta, double valor);


    public void transferir(Conta suaConta, Conta contaDestino, double valor);


    public void solicitarCartao(Conta conta) throws ParseException;

    public void exibirDadosBancarios(Conta conta);
}
