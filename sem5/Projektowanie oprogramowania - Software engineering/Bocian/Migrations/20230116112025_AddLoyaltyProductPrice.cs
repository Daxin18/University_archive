using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

#pragma warning disable CA1814 // Prefer jagged arrays over multidimensional

namespace Bocian.Migrations
{
    /// <inheritdoc />
    public partial class AddLoyaltyProductPrice : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_LoyaltySchemes_Products_ProductId",
                table: "LoyaltySchemes");

            migrationBuilder.DropColumn(
                name: "ProductInt",
                table: "LoyaltySchemes");

            migrationBuilder.AlterColumn<int>(
                name: "ProductId",
                table: "LoyaltySchemes",
                type: "integer",
                nullable: false,
                defaultValue: 0,
                oldClrType: typeof(int),
                oldType: "integer",
                oldNullable: true);

            migrationBuilder.AddColumn<decimal>(
                name: "DiscountPrice",
                table: "LoyaltySchemes",
                type: "numeric",
                nullable: false,
                defaultValue: 0m);

            migrationBuilder.InsertData(
                table: "LoyaltySchemes",
                columns: new[] { "Id", "DiscountPrice", "EndDate", "PointPrice", "ProductId", "StartDate" },
                values: new object[,]
                {
                    { 1, 6.00m, new DateOnly(9999, 12, 31), 2500, 1, new DateOnly(1, 1, 1) },
                    { 2, 18.00m, new DateOnly(9999, 12, 31), 3500, 3, new DateOnly(1, 1, 1) },
                    { 3, 8.00m, new DateOnly(2023, 1, 16), 500, 2, new DateOnly(1, 1, 1) }
                });

            migrationBuilder.AddForeignKey(
                name: "FK_LoyaltySchemes_Products_ProductId",
                table: "LoyaltySchemes",
                column: "ProductId",
                principalTable: "Products",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_LoyaltySchemes_Products_ProductId",
                table: "LoyaltySchemes");

            migrationBuilder.DeleteData(
                table: "LoyaltySchemes",
                keyColumn: "Id",
                keyValue: 1);

            migrationBuilder.DeleteData(
                table: "LoyaltySchemes",
                keyColumn: "Id",
                keyValue: 2);

            migrationBuilder.DeleteData(
                table: "LoyaltySchemes",
                keyColumn: "Id",
                keyValue: 3);

            migrationBuilder.DropColumn(
                name: "DiscountPrice",
                table: "LoyaltySchemes");

            migrationBuilder.AlterColumn<int>(
                name: "ProductId",
                table: "LoyaltySchemes",
                type: "integer",
                nullable: true,
                oldClrType: typeof(int),
                oldType: "integer");

            migrationBuilder.AddColumn<int>(
                name: "ProductInt",
                table: "LoyaltySchemes",
                type: "integer",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddForeignKey(
                name: "FK_LoyaltySchemes_Products_ProductId",
                table: "LoyaltySchemes",
                column: "ProductId",
                principalTable: "Products",
                principalColumn: "Id");
        }
    }
}
