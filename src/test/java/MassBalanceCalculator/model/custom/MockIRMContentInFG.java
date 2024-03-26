package MassBalanceCalculator.model.custom;

public class MockIRMContentInFG implements IRMContentInFG {
    private int id_wyrobu;
    private String index;
    private String nazwa_wyrobu;
    private String receptura;
    private Float amount_for_primary;
    private String indeks_CMJ;

    public MockIRMContentInFG(int id_wyrobu, String index, String nazwa_wyrobu, String receptura, Float amount_for_primary, String indeks_CMJ) {
        this.id_wyrobu = id_wyrobu;
        this.index = index;
        this.nazwa_wyrobu = nazwa_wyrobu;
        this.receptura = receptura;
        this.amount_for_primary = amount_for_primary;
        this.indeks_CMJ = indeks_CMJ;
    }

    @Override
    public int getID_wyrobu() {
        return id_wyrobu;
    }

    @Override
    public String getIndex() {
        return index;
    }

    @Override
    public String getNazwa_wyrobu() {
        return nazwa_wyrobu;
    }

    @Override
    public String getReceptura() {
        return receptura;
    }

    @Override
    public Float getAmount_for_primary() {
        return amount_for_primary;
    }

    @Override
    public String getIndeks_CMJ() {
        return indeks_CMJ;
    }
}