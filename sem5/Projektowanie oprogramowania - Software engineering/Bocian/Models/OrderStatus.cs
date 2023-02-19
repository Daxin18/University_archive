using System.ComponentModel.DataAnnotations;

namespace Bocian.Models;

public enum OrderStatus
{
    [Display(Name = "Oczekujące")]
    Pending, // klient złożył zamówienie

    [Display(Name = "Zatwierdzone")]
    Confirmed, // pracownik akceptował zamówienie i je pakuje

    [Display(Name = "Spakowane")]
    Packed, // pracownik spakował zamówienie i czeka aż przyjedzie kurier

    [Display(Name = "Dostarczane")]
    InDelivery, // kurier odebrał zamówienie i jest w drodze

    [Display(Name = "Zrealizowane")]
    Completed, // kurier dostarczył zamówienie, ma je klient

    [Display(Name = "Anulowane")]
    Canceled, // zamówienie oczekujące anuluje pracownik, zatwierdzone kierownik

    [Display(Name = "Zareklamowane")]
    Disputed // klient wysłał reklamację do zrealizowanego zamówienia
}
