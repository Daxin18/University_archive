using Microsoft.AspNetCore.Http;
using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Lista10.Models
{
    public class Article
    {
        public int Id { get; set; }
        [Required]
        public string Name { get; set; }
        [Required]
        public double Price { get; set; }
        [NotMapped]
        public IFormFile Photo { get; set; }
        public string? Image { get; set; }
        public Category Category { get; set; }
        [Required]
        public int CategoryId { get; set; }
    }
}
