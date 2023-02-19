using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Bocian.Migrations
{
    /// <inheritdoc />
    public partial class AddSecondAddress : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.InsertData(
                table: "Addresses",
                columns: new[] { "Id", "ApartmentNumber", "City", "HouseNumber", "PostalCode", "Street" },
                values: new object[] { 2, null, "Warszawa", 13, "58-123", "Fajna" });

            migrationBuilder.UpdateData(
                table: "LoyaltySchemes",
                keyColumn: "Id",
                keyValue: 3,
                column: "EndDate",
                value: new DateOnly(2023, 1, 18));

            migrationBuilder.UpdateData(
                table: "Orders",
                keyColumn: "Id",
                keyValue: 1,
                column: "AddressId",
                value: 2);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DeleteData(
                table: "Addresses",
                keyColumn: "Id",
                keyValue: 2);

            migrationBuilder.UpdateData(
                table: "LoyaltySchemes",
                keyColumn: "Id",
                keyValue: 3,
                column: "EndDate",
                value: new DateOnly(2023, 1, 17));

            migrationBuilder.UpdateData(
                table: "Orders",
                keyColumn: "Id",
                keyValue: 1,
                column: "AddressId",
                value: 1);
        }
    }
}
