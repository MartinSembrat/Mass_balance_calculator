package MassBalanceCalculator.repository;

import MassBalanceCalculator.model.custom.IRMContentInFG;
import MassBalanceCalculator.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISaleRepository extends JpaRepository<Sale, Integer> {

    @Query(value = "SELECT Index FROM tbl_surowce", nativeQuery = true)
    List<String> findAllRMIndexes();

    @Query(value = "SELECT tbl_wyroby.ID_wyrobu, tbl_surowce.Index, tbl_wyroby.Nazwa_wyrobu, tbl_surowce.Index AS Receptura, tbl_wyroby_sklad.Naważka AS Amount_for_primary, tbl_wyroby.INDEKS_CMJ\n" +
            "FROM tbl_surowce INNER JOIN (tbl_wyroby_sklad INNER JOIN tbl_wyroby ON tbl_wyroby_sklad.ID_wyrobu = tbl_wyroby.ID_wyrobu) ON tbl_surowce.ID_surowca = tbl_wyroby_sklad.ID_skladnika\n" +
            "WHERE (((tbl_surowce.Index)=:index) AND ((tbl_wyroby_sklad.ID_typskladnika)=1))", nativeQuery = true)
    List<IRMContentInFG> findIRMContentInFG(@Param("index") String index);

    @Query(value = "SELECT tbl_wyroby.ID_wyrobu, tbl_surowce.Index, tbl_wyroby.Nazwa_wyrobu, tbl_ciasto_calosc.Nr_receptury AS Receptura, CAST(tbl_ciasto_calosc.Ilosc_surowca AS REAL)* tbl_wyroby_sklad.Naważka/qry_Wagi_mixu_ciasto .Waga_Mixu AS Amount_for_primary, tbl_wyroby.Indeks_CMJ\n" +
            "FROM (SELECT tbl_ciasto_calosc.ID_ciasta, Sum(CAST(tbl_ciasto_calosc.Ilosc_surowca AS REAL)) AS Waga_Mixu, tbl_ciasto_calosc.Wersja\n" +
            "FROM tbl_ciasto_calosc\n" +
            "GROUP BY tbl_ciasto_calosc.ID_ciasta, tbl_ciasto_calosc.Wersja) qry_Wagi_mixu_ciasto RIGHT JOIN (((tbl_surowce INNER JOIN tbl_ciasto_calosc ON tbl_surowce.ID_surowca = tbl_ciasto_calosc.ID_surowca) INNER JOIN tbl_ciasto ON tbl_ciasto_calosc.ID_ciasta = tbl_ciasto.ID_ciasta) INNER JOIN (tbl_wyroby_sklad INNER JOIN tbl_wyroby ON tbl_wyroby_sklad.ID_wyrobu = tbl_wyroby.ID_wyrobu) ON tbl_ciasto_calosc.ID_ciasta = tbl_wyroby_sklad.ID_skladnika) ON qry_Wagi_mixu_ciasto.ID_ciasta = tbl_ciasto_calosc.ID_ciasta\n" +
            "WHERE (((tbl_surowce.Index)=:index) AND ((tbl_wyroby.Indeks_CMJ) Is Not Null) AND ((tbl_wyroby_sklad.ID_typskladnika)=2) AND ((tbl_ciasto_calosc.Wersja)=tbl_ciasto.Wersja) AND ((tbl_ciasto.Aktualna)=1) AND ((qry_Wagi_mixu_ciasto.Wersja)=tbl_ciasto.Wersja));", nativeQuery = true)
    List<IRMContentInFG> findIRMContentInFGCakes(@Param("index") String index);

    @Query(value = "SELECT tbl_wyroby.ID_wyrobu, tbl_surowce.Index, tbl_wyroby.Nazwa_wyrobu, \n" +
            "tbl_krem_calosc.Nr_receptury AS Receptura, CAST(tbl_krem_calosc.Ilosc AS REAL)* tbl_wyroby_sklad.Naważka/qry_Wagi_mixu_krem .Waga_Mixu AS Amount_for_primary, \n" +
            "tbl_wyroby.Indeks_CMJ\n" +
            "FROM (SELECT tbl_krem_calosc.ID_kremu, Sum(CAST(tbl_krem_calosc.Ilosc AS REAL)) AS Waga_Mixu, tbl_krem_calosc.Wersja\n" +
            "FROM tbl_krem_calosc\n" +
            "GROUP BY tbl_krem_calosc.ID_kremu, tbl_krem_calosc.Wersja) qry_Wagi_mixu_krem RIGHT JOIN (((tbl_surowce INNER JOIN tbl_krem_calosc ON tbl_surowce.ID_surowca = tbl_krem_calosc.ID_surowca) INNER JOIN tbl_kremy ON tbl_krem_calosc.ID_kremu = tbl_kremy.ID_kremu) INNER JOIN (tbl_wyroby_sklad INNER JOIN tbl_wyroby ON tbl_wyroby_sklad.ID_wyrobu = tbl_wyroby.ID_wyrobu) ON tbl_krem_calosc.ID_kremu = tbl_wyroby_sklad.ID_skladnika) ON qry_Wagi_mixu_krem.ID_kremu = tbl_krem_calosc.ID_kremu\n" +
            "WHERE (((tbl_surowce.Index)=:index) AND ((tbl_wyroby.Indeks_CMJ) Is Not Null) AND ((tbl_wyroby_sklad.ID_typskladnika)=2) AND ((tbl_krem_calosc.Wersja)=tbl_kremy.Wersja) AND ((tbl_kremy.Aktualna)=1) AND ((qry_Wagi_mixu_krem.Wersja)=tbl_kremy.Wersja));", nativeQuery = true)
    List<IRMContentInFG> findIRMContentInFGFillings(@Param("index") String index);


}
