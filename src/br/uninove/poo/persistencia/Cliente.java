package br.uninove.poo.persistencia;

public class Cliente {
	 
    private int id;
    private String nome;
    private String telefone;
    private String sexo;
    private double renda;
 
    public Cliente() {
    }
 
    public Cliente(int id, String nome, String telefone, String sexo, double renda) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.renda = renda;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getNome() {
        return nome;
    }
 
    public void setNome(String nome) {
        this.nome = nome;
    }
 
    public String getTelefone() {
        return telefone;
    }
 
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
 
    public String getSexo() {
        return sexo;
    }
 
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
 
    public double getRenda() {
        return renda;
    }
 
    public void setRenda(double renda) {
        this.renda = renda;
    }
}