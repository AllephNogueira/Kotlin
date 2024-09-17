package Veiculo;

public class Carro extends Veiculo {
    private String cambio;
    private String tipoDeCombustivel;
    private String tipoDeOleo;


    public String getCambio() {
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }

    public String getTipoDeCombustivel() {
        return tipoDeCombustivel;
    }

    public void setTipoDeCombustivel(String tipoDeCombustivel) {
        this.tipoDeCombustivel = tipoDeCombustivel;
    }

    public String setTipoDeOleo(Veiculo veiculo){
        return tipoDeOleo = veiculo.getMarca();
//        return tipoDeOleo;
    }

    @Override
    public String toString() {
        return "Marca " + super.getMarca() + " placa " + super.getPlaca() + " cambio: " + this.cambio + " tipo de combustivel " + this.tipoDeCombustivel + " - TESTE " + this.tipoDeOleo;
    }
}
