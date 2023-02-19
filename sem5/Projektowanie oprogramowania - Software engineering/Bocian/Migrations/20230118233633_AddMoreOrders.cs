using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

#pragma warning disable CA1814 // Prefer jagged arrays over multidimensional

namespace Bocian.Migrations
{
    /// <inheritdoc />
    public partial class AddMoreOrders : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.UpdateData(
                table: "Accounts",
                keyColumn: "Id",
                keyValue: 5,
                columns: new[] { "FirstName", "LastName" },
                values: new object[] { "Andrzej", "Kowalski" });

            migrationBuilder.UpdateData(
                table: "Accounts",
                keyColumn: "Id",
                keyValue: 6,
                columns: new[] { "FirstName", "LastName" },
                values: new object[] { "Anna", "Kowal" });

            migrationBuilder.InsertData(
                table: "Addresses",
                columns: new[] { "Id", "ApartmentNumber", "City", "HouseNumber", "PostalCode", "Street" },
                values: new object[,]
                {
                    { 3, 1, "Opole", 18, "57-420", "Słoneczna" },
                    { 4, null, "Kraków", 1, "47-100", "Stara" },
                    { 5, 7, "Warszawa", 123, "58-333", "Szeroka" }
                });

            migrationBuilder.InsertData(
                table: "Carts",
                columns: new[] { "Id", "ClientId" },
                values: new object[,]
                {
                    { 2, 4 },
                    { 3, 5 },
                    { 4, 6 }
                });

            migrationBuilder.InsertData(
                table: "Payments",
                columns: new[] { "Id", "PaymentTime", "PaymentType", "TotalPrice" },
                values: new object[,]
                {
                    { 2, null, 2, 0m },
                    { 3, null, 2, 0m },
                    { 4, null, 2, 0m }
                });

            migrationBuilder.InsertData(
                table: "Orders",
                columns: new[] { "Id", "AddressId", "CartId", "ClientId", "EmployeeId", "OrderedAt", "PaymentId", "Status" },
                values: new object[,]
                {
                    { 2, 3, 2, 4, null, new DateTimeOffset(new DateTime(2023, 1, 12, 15, 18, 5, 0, DateTimeKind.Unspecified), new TimeSpan(0, 1, 0, 0, 0)), 2, 0 },
                    { 3, 4, 3, 5, null, new DateTimeOffset(new DateTime(2023, 1, 11, 4, 13, 18, 0, DateTimeKind.Unspecified), new TimeSpan(0, 1, 0, 0, 0)), 3, 0 },
                    { 4, 5, 4, 6, null, new DateTimeOffset(new DateTime(2023, 1, 15, 21, 37, 59, 0, DateTimeKind.Unspecified), new TimeSpan(0, 1, 0, 0, 0)), 4, 0 }
                });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DeleteData(
                table: "Orders",
                keyColumn: "Id",
                keyValue: 2);

            migrationBuilder.DeleteData(
                table: "Orders",
                keyColumn: "Id",
                keyValue: 3);

            migrationBuilder.DeleteData(
                table: "Orders",
                keyColumn: "Id",
                keyValue: 4);

            migrationBuilder.DeleteData(
                table: "Addresses",
                keyColumn: "Id",
                keyValue: 3);

            migrationBuilder.DeleteData(
                table: "Addresses",
                keyColumn: "Id",
                keyValue: 4);

            migrationBuilder.DeleteData(
                table: "Addresses",
                keyColumn: "Id",
                keyValue: 5);

            migrationBuilder.DeleteData(
                table: "Carts",
                keyColumn: "Id",
                keyValue: 2);

            migrationBuilder.DeleteData(
                table: "Carts",
                keyColumn: "Id",
                keyValue: 3);

            migrationBuilder.DeleteData(
                table: "Carts",
                keyColumn: "Id",
                keyValue: 4);

            migrationBuilder.DeleteData(
                table: "Payments",
                keyColumn: "Id",
                keyValue: 2);

            migrationBuilder.DeleteData(
                table: "Payments",
                keyColumn: "Id",
                keyValue: 3);

            migrationBuilder.DeleteData(
                table: "Payments",
                keyColumn: "Id",
                keyValue: 4);

            migrationBuilder.UpdateData(
                table: "Accounts",
                keyColumn: "Id",
                keyValue: 5,
                columns: new[] { "FirstName", "LastName" },
                values: new object[] { "Lorem", "Ipsum" });

            migrationBuilder.UpdateData(
                table: "Accounts",
                keyColumn: "Id",
                keyValue: 6,
                columns: new[] { "FirstName", "LastName" },
                values: new object[] { "Lorem", "Ipsum" });
        }
    }
}
