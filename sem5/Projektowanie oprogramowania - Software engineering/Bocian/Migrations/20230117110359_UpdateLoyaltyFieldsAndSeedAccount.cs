using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Bocian.Migrations
{
    /// <inheritdoc />
    public partial class UpdateLoyaltyFieldsAndSeedAccount : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterColumn<decimal>(
                name: "DiscountPrice",
                table: "LoyaltySchemes",
                type: "numeric(18,2)",
                precision: 18,
                scale: 2,
                nullable: false,
                oldClrType: typeof(decimal),
                oldType: "numeric");

            migrationBuilder.UpdateData(
                table: "Clients",
                keyColumn: "Id",
                keyValue: 4,
                column: "OwnedPoints",
                value: 3000);

            migrationBuilder.UpdateData(
                table: "LoyaltySchemes",
                keyColumn: "Id",
                keyValue: 3,
                column: "EndDate",
                value: new DateOnly(2023, 1, 17));
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterColumn<decimal>(
                name: "DiscountPrice",
                table: "LoyaltySchemes",
                type: "numeric",
                nullable: false,
                oldClrType: typeof(decimal),
                oldType: "numeric(18,2)",
                oldPrecision: 18,
                oldScale: 2);

            migrationBuilder.UpdateData(
                table: "Clients",
                keyColumn: "Id",
                keyValue: 4,
                column: "OwnedPoints",
                value: 0);

            migrationBuilder.UpdateData(
                table: "LoyaltySchemes",
                keyColumn: "Id",
                keyValue: 3,
                column: "EndDate",
                value: new DateOnly(2023, 1, 16));
        }
    }
}
