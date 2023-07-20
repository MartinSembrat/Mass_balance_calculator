package MassBalanceCalculator.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;


@Setter
@Getter
@Entity
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
        BigInteger ilosc;
        BigInteger iloscKG;
        Float wartoscNetto;
        }
