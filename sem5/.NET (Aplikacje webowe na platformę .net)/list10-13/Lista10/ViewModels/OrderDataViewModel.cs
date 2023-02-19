using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Lista10.Models;

namespace Lista10.ViewModels
{
    public enum PaymentMethod
    {
        Card,
        Cash,
        Unspecified
    }

    public class OrderDataViewModel
    {
        public List<(Article article, string quantity)> Articles { get; set; }
        public string Name { get; set; }
        public string Surname { get; set; }
        public string Address { get; set; }
        public string Payment_method { get; set; } //snake case to avoid conflicts with PaymentMethod enum

        public static PaymentMethod TranslatePayment(string input)
        {
            switch (input)
            {
                case "Karta":
                    return PaymentMethod.Card;
                case "Gotówka":
                    return PaymentMethod.Cash;
                default:
                    return PaymentMethod.Unspecified;
            }
        }

        public static string UnTranslatePayment(PaymentMethod input)
        {
            switch (input)
            {
                case PaymentMethod.Card:
                    return "Karta";
                case PaymentMethod.Cash:
                    return "Gotówka";
                default:
                    return "";
            }
        }
    }
}
