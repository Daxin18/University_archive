using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

#pragma warning disable CA1814 // Prefer jagged arrays over multidimensional

namespace Bocian.Migrations
{
    /// <inheritdoc />
    public partial class SeedProducts : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.InsertData(
                table: "Categories",
                columns: new[] { "Id", "Name" },
                values: new object[] { 1, "Fast food" });

            migrationBuilder.InsertData(
                table: "Images",
                columns: new[] { "Id", "Url" },
                values: new object[,]
                {
                    { 1, "https://upload.wikimedia.org/wikipedia/commons/c/c0/Pizza_with_tomatoes.jpg" },
                    { 2, "https://upload.wikimedia.org/wikipedia/commons/2/28/Polish_%22Zapiekanka%22.jpg" }
                });

            migrationBuilder.UpdateData(
                table: "Orders",
                keyColumn: "Id",
                keyValue: 1,
                column: "OrderedAt",
                value: new DateTimeOffset(new DateTime(2023, 1, 12, 9, 37, 10, 0, DateTimeKind.Unspecified), new TimeSpan(0, 1, 0, 0, 0)));

            migrationBuilder.InsertData(
                table: "Products",
                columns: new[] { "Id", "CategoryId", "ImageId", "Name", "OnlyForAdults", "Price", "Weight" },
                values: new object[,]
                {
                    { 1, 1, 1, "Pizza", false, 13.00m, 0.29999999999999999 },
                    { 2, 1, 2, "Zapiekanka", false, 7.00m, 0.23000000000000001 }
                });

            migrationBuilder.InsertData(
                table: "CartItems",
                columns: new[] { "CartId", "ProductId", "ItemCount" },
                values: new object[,]
                {
                    { 1, 1, 1 },
                    { 1, 2, 3 }
                });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DeleteData(
                table: "CartItems",
                keyColumns: new[] { "CartId", "ProductId" },
                keyValues: new object[] { 1, 1 });

            migrationBuilder.DeleteData(
                table: "CartItems",
                keyColumns: new[] { "CartId", "ProductId" },
                keyValues: new object[] { 1, 2 });

            migrationBuilder.DeleteData(
                table: "Products",
                keyColumn: "Id",
                keyValue: 1);

            migrationBuilder.DeleteData(
                table: "Products",
                keyColumn: "Id",
                keyValue: 2);

            migrationBuilder.DeleteData(
                table: "Categories",
                keyColumn: "Id",
                keyValue: 1);

            migrationBuilder.DeleteData(
                table: "Images",
                keyColumn: "Id",
                keyValue: 1);

            migrationBuilder.DeleteData(
                table: "Images",
                keyColumn: "Id",
                keyValue: 2);

            migrationBuilder.UpdateData(
                table: "Orders",
                keyColumn: "Id",
                keyValue: 1,
                column: "OrderedAt",
                value: new DateTimeOffset(new DateTime(2023, 1, 12, 0, 0, 0, 0, DateTimeKind.Unspecified), new TimeSpan(0, 1, 0, 0, 0)));
        }
    }
}
