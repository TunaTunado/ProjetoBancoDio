package br.com.dio.dominio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class CartaoDeCredito {

    private Integer numeroCartao;//o numero do cartao deve ter 16 digitos

    private Date validade;

    private Integer codigoSeguranca; //o codigo de seguranca tem q ter 3 digitos

    public CartaoDeCredito() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        Random random = new Random();

        this.numeroCartao = random.nextInt(16);
        this.codigoSeguranca = random.nextInt(3);
        this.validade = simpleDateFormat.parse("01/01/2030");
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public Integer getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(Integer codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }

    public Integer getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(Integer numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
}
