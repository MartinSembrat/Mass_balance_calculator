package MassBalanceCalculator.repository;

import MassBalanceCalculator.model.custom.IRMContentInFG;
import MassBalanceCalculator.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISaleRepository extends JpaRepository<Sale, Integer> {

    @Query(value=qry, nativeQuery = true)
    List<IRMContentInFG> findWhereIndexOccursAmount (@Param("index")String index);

    String qry = "SELECT tbl_surowce.Index, tbl_wyroby.Nazwa_wyrobu, tbl_surowce.Index AS [Nr receptury], tbl_wyroby_sklad.Naważka AS Amount_for_primary\n" +
            "FROM tbl_surowce INNER JOIN (tbl_wyroby_sklad INNER JOIN tbl_wyroby ON tbl_wyroby_sklad.ID_wyrobu = tbl_wyroby.ID_wyrobu) ON tbl_surowce.ID_surowca = tbl_wyroby_sklad.ID_skladnika\n" +
            "WHERE (((tbl_surowce.Index)=\":index\") AND ((tbl_wyroby_sklad.ID_typskladnika)=1) AND ((tbl_wyroby.Flaga)=False));\n" +
            "UNION ALL\n" +
            "SELECT tbl_surowce.Index, tbl_wyroby.Nazwa_wyrobu, tbl_ciasto_calosc.[Nr receptury], [Ilosc surowca]*[Naważka]/[Waga_Mixu] AS Amount_for_primary\n" +
            "FROM qry_Wagi_mixu_ciasto RIGHT JOIN (((tbl_surowce INNER JOIN tbl_ciasto_calosc ON tbl_surowce.ID_surowca = tbl_ciasto_calosc.ID_surowca) INNER JOIN tbl_ciasto ON tbl_ciasto_calosc.ID_ciasta = tbl_ciasto.ID_ciasta) INNER JOIN (tbl_wyroby_sklad INNER JOIN tbl_wyroby ON tbl_wyroby_sklad.ID_wyrobu = tbl_wyroby.ID_wyrobu) ON tbl_ciasto_calosc.ID_ciasta = tbl_wyroby_sklad.ID_skladnika) ON qry_Wagi_mixu_ciasto.ID_ciasta = tbl_ciasto_calosc.ID_ciasta\n" +
            "WHERE (((tbl_surowce.Index)=\":index\") AND ((tbl_wyroby_sklad.ID_typskladnika)=2) AND ((tbl_wyroby.Flaga)=False) AND ((tbl_ciasto_calosc.Wersja)=[tbl_ciasto].[Wersja]) AND ((tbl_ciasto.Aktualna)=True) AND ((qry_Wagi_mixu_ciasto.Wersja)=[tbl_ciasto].[Wersja]));\n" +
            "UNION ALL\n" +
            "SELECT tbl_surowce.Index, tbl_wyroby.Nazwa_wyrobu, tbl_krem_całosc.[Nr receptury], [Ilosc]*[Naważka]/[Waga_Mixu] AS Amount_for_primary\n" +
            "FROM tbl_kremy INNER JOIN (qry_Wagi_mixu_krem RIGHT JOIN ((tbl_surowce INNER JOIN tbl_krem_całosc ON tbl_surowce.ID_surowca = tbl_krem_całosc.ID_surowca) INNER JOIN (tbl_wyroby_sklad INNER JOIN tbl_wyroby ON tbl_wyroby_sklad.ID_wyrobu = tbl_wyroby.ID_wyrobu) ON tbl_krem_całosc.ID_kremu = tbl_wyroby_sklad.ID_skladnika) ON qry_Wagi_mixu_krem.ID_kremu = tbl_krem_całosc.ID_kremu) ON tbl_kremy.ID_kremu = tbl_krem_całosc.ID_kremu\n" +
            "WHERE (((tbl_surowce.Index)=\":index\") AND ((tbl_wyroby_sklad.ID_typskladnika)=3) AND ((tbl_wyroby.Flaga)=False) AND ((qry_Wagi_mixu_krem.wersja)=[tbl_kremy].[wersja]) AND ((tbl_kremy.Aktualna)=True) AND ((tbl_krem_całosc.wersja)=[tbl_kremy].[wersja]));\n" +
            "UNION ALL\n" +
            "SELECT tbl_surowce.Index, tbl_wyroby.Nazwa_wyrobu, tbl_krem_całosc.[Nr receptury], ([tbl_krem_całosc].[Ilosc]*[tbl_krem_całosc_1].[Ilosc]/[qry_Wagi_mixu_krem].[Waga_Mixu])*[tbl_wyroby_sklad].[Naważka]/[qry_Wagi_mixu_krem_1].[Waga_Mixu] AS Amount_for_primary\n" +
            "FROM qry_Wagi_mixu_krem AS qry_Wagi_mixu_krem_1 INNER JOIN ((tbl_kremy AS tbl_kremy_1 INNER JOIN (((tbl_kremy INNER JOIN (tbl_surowce INNER JOIN tbl_krem_całosc ON tbl_surowce.ID_surowca = tbl_krem_całosc.ID_surowca) ON tbl_kremy.ID_kremu = tbl_krem_całosc.ID_kremu) INNER JOIN tbl_krem_całosc AS tbl_krem_całosc_1 ON tbl_krem_całosc.ID_kremu = tbl_krem_całosc_1.ID_surowca) INNER JOIN (tbl_wyroby_sklad INNER JOIN tbl_wyroby ON tbl_wyroby_sklad.ID_wyrobu = tbl_wyroby.ID_wyrobu) ON tbl_krem_całosc_1.ID_kremu = tbl_wyroby_sklad.ID_skladnika) ON tbl_kremy_1.ID_kremu = tbl_krem_całosc_1.ID_kremu) LEFT JOIN qry_Wagi_mixu_krem ON tbl_kremy.ID_kremu = qry_Wagi_mixu_krem.ID_kremu) ON qry_Wagi_mixu_krem_1.ID_kremu = tbl_kremy_1.ID_kremu\n" +
            "WHERE (((tbl_surowce.Index)=\":index\") AND ((tbl_wyroby_sklad.ID_typskladnika)=3) AND ((tbl_wyroby.Flaga)=False) AND ((tbl_krem_całosc.wersja)=[tbl_kremy].[wersja]) AND ((tbl_kremy.Aktualna)=True) AND ((tbl_krem_całosc_1.wersja)=[tbl_kremy].[wersja]) AND ((tbl_krem_całosc_1.ID_typskladnika)=3) AND ((tbl_kremy_1.Aktualna)=True) AND ((qry_Wagi_mixu_krem.wersja)=[tbl_kremy].[wersja]) AND ((qry_Wagi_mixu_krem_1.wersja)=[tbl_kremy_1].[wersja]));\n";


}
