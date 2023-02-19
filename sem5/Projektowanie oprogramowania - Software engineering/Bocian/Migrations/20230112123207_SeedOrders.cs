using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Bocian.Migrations
{
    /// <inheritdoc />
    public partial class SeedOrders : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.InsertData(
                table: "Addresses",
                columns: new[] { "Id", "ApartmentNumber", "City", "HouseNumber", "PostalCode", "Street" },
                values: new object[] { 1, 5, "Wrocław", 18, "58-100", "Słowackiego" });

            migrationBuilder.InsertData(
                table: "Carts",
                columns: new[] { "Id", "ClientId" },
                values: new object[] { 1, 4 });

            migrationBuilder.InsertData(
                table: "Payments",
                columns: new[] { "Id", "PaymentTime", "PaymentType", "TotalPrice" },
                values: new object[] { 1, null, 2, 0m });

            migrationBuilder.InsertData(
                table: "Orders",
                columns: new[] { "Id", "AddressId", "CartId", "ClientId", "OrderedAt", "PaymentId", "Status" },
                values: new object[] { 1, 1, 1, 4, new DateTimeOffset(new DateTime(2023, 1, 12, 0, 0, 0, 0, DateTimeKind.Unspecified), new TimeSpan(0, 1, 0, 0, 0)), 1, 0 });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DeleteData(
                table: "Orders",
                keyColumn: "Id",
                keyValue: 1);

            migrationBuilder.DeleteData(
                table: "Addresses",
                keyColumn: "Id",
                keyValue: 1);

            migrationBuilder.DeleteData(
                table: "Carts",
                keyColumn: "Id",
                keyValue: 1);

            migrationBuilder.DeleteData(
                table: "Payments",
                keyColumn: "Id",
                keyValue: 1);
        }
    }
}
