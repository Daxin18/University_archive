using System;
using System.ComponentModel.DataAnnotations;

namespace Lista10.Models
{
    public class Category
    {
        public int Id { get; set; }
        [Required]
        [MinLength(2, ErrorMessage ="The name is too short")]
        [MaxLength(32, ErrorMessage ="The name is too long")]
        public string Name { get; set; }
    }
}
