using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

#pragma warning disable CA1814 // Prefer jagged arrays over multidimensional

namespace Bocian.Migrations
{
    /// <inheritdoc />
    public partial class AddProductDescription : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<string>(
                name: "Description",
                table: "Products",
                type: "character varying(1000)",
                maxLength: 1000,
                nullable: false,
                defaultValue: "");

            migrationBuilder.InsertData(
                table: "Categories",
                columns: new[] { "Id", "Name" },
                values: new object[] { 2, "Nabiał" });

            migrationBuilder.InsertData(
                table: "Images",
                columns: new[] { "Id", "Url" },
                values: new object[,]
                {
                    { 3, "https://s3.party.pl/newsy/zolty-ser-307663-16_9.jpg" },
                    { 4, "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0e/Milk_glass.jpg/330px-Milk_glass.jpg" }
                });

            migrationBuilder.UpdateData(
                table: "Products",
                keyColumn: "Id",
                keyValue: 1,
                column: "Description",
                value: "Potrawa kuchni włoskiej, obecnie szeroko rozpowszechniona na całym świecie.");

            migrationBuilder.UpdateData(
                table: "Products",
                keyColumn: "Id",
                keyValue: 2,
                column: "Description",
                value: "Polski fast food, powstały w latach 70. XX wieku, w okresie Polskiej Rzeczypospolitej Ludowej.");

            migrationBuilder.InsertData(
                table: "Products",
                columns: new[] { "Id", "CategoryId", "Description", "ImageId", "Name", "OnlyForAdults", "Price", "Weight" },
                values: new object[,]
                {
                    { 3, 2, "Produkt spożywczy wytwarzany poprzez wytrącenie z mleka tłuszczu i białka w postaci skrzepu, który zostaje poddany dalszej obróbce.", 3, "Ser", false, 23.00m, 1.0 },
                    { 4, 2, "Mleko ma liczne wartości odżywcze - w jego skład wchodzą witaminy i pierwiastki mineralne, odpowiedzialne za prawidłowe funkcjonowanie organizmu.", 4, "Mleko", false, 1.50m, 1.0029999999999999 }
                });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DeleteData(
                table: "Products",
                keyColumn: "Id",
                keyValue: 3);

            migrationBuilder.DeleteData(
                table: "Products",
                keyColumn: "Id",
                keyValue: 4);

            migrationBuilder.DeleteData(
                table: "Categories",
                keyColumn: "Id",
                keyValue: 2);

            migrationBuilder.DeleteData(
                table: "Images",
                keyColumn: "Id",
                keyValue: 3);

            migrationBuilder.DeleteData(
                table: "Images",
                keyColumn: "Id",
                keyValue: 4);

            migrationBuilder.DropColumn(
                name: "Description",
                table: "Products");
        }
    }
}
