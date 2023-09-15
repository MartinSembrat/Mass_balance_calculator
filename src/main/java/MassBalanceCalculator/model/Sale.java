package MassBalanceCalculator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@ToString
@Setter
@Getter
@Entity
@Table(name = "sales")
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    Integer id;
    Integer nr_Mag;
    Integer idPlatnika;
    String platnik_Nazwa;
    Integer idOdbiorcy;
    String odbiorca_Nazwa;
    Date dataWystawienia;
    Integer wk;
    String indeks;
    String nazwa;
    String jm;
    Float ilosc;
    Float iloscKG;
    BigInteger wartoscNetto;

    public Sale(Integer id, Integer nr_Mag, Integer idPlatnika, String platnik_Nazwa, Integer idOdbiorcy, String odbiorca_Nazwa, Date dataWystawienia, Integer wk, String indeks, String nazwa, String jm, Float ilosc, Float iloscKG, BigInteger wartoscNetto) {
        this.id = id;
        this.nr_Mag = nr_Mag;
        this.idPlatnika = idPlatnika;
        this.platnik_Nazwa = platnik_Nazwa;
        this.idOdbiorcy = idOdbiorcy;
        this.odbiorca_Nazwa = odbiorca_Nazwa;
        this.dataWystawienia = dataWystawienia;
        this.wk = wk;
        this.indeks = indeks;
        this.nazwa = nazwa;
        this.jm = jm;
        this.ilosc = ilosc;
        this.iloscKG = iloscKG;
        this.wartoscNetto = wartoscNetto;
    }

}
