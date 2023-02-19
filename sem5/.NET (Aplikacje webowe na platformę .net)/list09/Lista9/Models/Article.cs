using System;
using System.ComponentModel.DataAnnotations;

namespace Lista9.Models
{
    public enum ArticleCategory
    {
        Food,
        Toy,
        Clothing,
        Other
    }

    public class Article
    {
        [Key]
        public int Id { get; set; }
        [MinLength(2)]
        [MaxLength(40)]
        public string Name { get; set; }
        [Range(0,double.MaxValue)]
        public double Price { get; set; }
        [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd}", ApplyFormatInEditMode = true)]
        public DateTime ExpiryDate { get; set; }
        public ArticleCategory Category { get; set; }
    }
}
