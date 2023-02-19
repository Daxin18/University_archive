using System;
using Microsoft.EntityFrameworkCore.Migrations;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

#nullable disable

#pragma warning disable CA1814 // Prefer jagged arrays over multidimensional

namespace Bocian.Migrations
{
    /// <inheritdoc />
    public partial class MoreSeeeeed : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_ShopIndexVm_Addresses_AddressId",
                table: "ShopIndexVm");

            migrationBuilder.CreateTable(
                name: "AddressVm",
                columns: table => new
                {
                    Id = table.Column<int>(type: "integer", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    PostalCode = table.Column<string>(type: "text", nullable: false),
                    City = table.Column<string>(type: "text", nullable: false),
                    Street = table.Column<string>(type: "text", nullable: false),
                    HouseNumber = table.Column<int>(type: "integer", nullable: false),
                    ApartmentNumber = table.Column<int>(type: "integer", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_AddressVm", x => x.Id);
                });

            migrationBuilder.InsertData(
                table: "Accounts",
                columns: new[] { "Id", "Email", "FirstName", "LastName" },
                values: new object[,]
                {
                    { 5, "klient1@ebocian.pl", "Lorem", "Ipsum" },
                    { 6, "klient2@ebocian.pl", "Lorem", "Ipsum" },
                    { 1001, "pracownikSlup@ebocian.pl", "Pracownik", "Slup" }
                });

            migrationBuilder.InsertData(
                table: "Carts",
                columns: new[] { "Id", "ClientId" },
                values: new object[,]
                {
                    { 1001, 4 },
                    { 1004, 4 }
                });

            migrationBuilder.InsertData(
                table: "Payments",
                columns: new[] { "Id", "PaymentTime", "PaymentType", "TotalPrice" },
                values: new object[,]
                {
                    { 1001, null, 2, 0m },
                    { 1002, null, 2, 0m },
                    { 1003, null, 2, 0m },
                    { 1004, null, 2, 0m },
                    { 1005, null, 2, 0m },
                    { 1006, null, 2, 0m }
                });

            migrationBuilder.InsertData(
                table: "CartItems",
                columns: new[] { "CartId", "ProductId", "ItemCount" },
                values: new object[,]
                {
                    { 1001, 1, 5 },
                    { 1004, 4, 3 }
                });

            migrationBuilder.InsertData(
                table: "Clients",
                columns: new[] { "Id", "OwnedPoints", "PhoneNumber", "SpentPoints" },
                values: new object[,]
                {
                    { 5, 0, "+48 998 997 999", 0 },
                    { 6, 0, "+48 111 222 333", 0 }
                });

            migrationBuilder.InsertData(
                table: "Employees",
                columns: new[] { "Id", "ShopId" },
                values: new object[] { 1001, 1001 });

            migrationBuilder.InsertData(
                table: "Carts",
                columns: new[] { "Id", "ClientId" },
                values: new object[,]
                {
                    { 1002, 5 },
                    { 1003, 6 },
                    { 1005, 5 },
                    { 1006, 6 }
                });

            migrationBuilder.InsertData(
                table: "Orders",
                columns: new[] { "Id", "AddressId", "CartId", "ClientId", "EmployeeId", "OrderedAt", "PaymentId", "Status" },
                values: new object[,]
                {
                    { 1001, 1004, 1001, 4, 1001, new DateTimeOffset(new DateTime(2023, 1, 12, 9, 37, 10, 0, DateTimeKind.Unspecified), new TimeSpan(0, 1, 0, 0, 0)), 1001, 1 },
                    { 1004, 1001, 1004, 4, 1001, new DateTimeOffset(new DateTime(2023, 1, 15, 9, 37, 10, 0, DateTimeKind.Unspecified), new TimeSpan(0, 1, 0, 0, 0)), 1004, 4 }
                });

            migrationBuilder.InsertData(
                table: "CartItems",
                columns: new[] { "CartId", "ProductId", "ItemCount" },
                values: new object[,]
                {
                    { 1005, 1, 1 },
                    { 1002, 2, 2 },
                    { 1006, 2, 25 },
                    { 1003, 3, 7 }
                });

            migrationBuilder.InsertData(
                table: "Orders",
                columns: new[] { "Id", "AddressId", "CartId", "ClientId", "EmployeeId", "OrderedAt", "PaymentId", "Status" },
                values: new object[,]
                {
                    { 1002, 1003, 1002, 5, 1001, new DateTimeOffset(new DateTime(2023, 1, 13, 9, 37, 10, 0, DateTimeKind.Unspecified), new TimeSpan(0, 1, 0, 0, 0)), 1002, 2 },
                    { 1003, 1002, 1003, 6, 1001, new DateTimeOffset(new DateTime(2023, 1, 14, 9, 37, 10, 0, DateTimeKind.Unspecified), new TimeSpan(0, 1, 0, 0, 0)), 1003, 3 },
                    { 1005, 2, 1005, 5, 1001, new DateTimeOffset(new DateTime(2023, 1, 16, 9, 37, 10, 0, DateTimeKind.Unspecified), new TimeSpan(0, 1, 0, 0, 0)), 1005, 5 },
                    { 1006, 1, 1006, 6, 1001, new DateTimeOffset(new DateTime(2023, 1, 17, 9, 37, 10, 0, DateTimeKind.Unspecified), new TimeSpan(0, 1, 0, 0, 0)), 1006, 6 }
                });

            migrationBuilder.AddForeignKey(
                name: "FK_ShopIndexVm_AddressVm_AddressId",
                table: "ShopIndexVm",
                column: "AddressId",
                principalTable: "AddressVm",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_ShopIndexVm_AddressVm_AddressId",
                table: "ShopIndexVm");

            migrationBuilder.DropTable(
                name: "AddressVm");

            migrationBuilder.DeleteData(
                table: "CartItems",
                keyColumns: new[] { "CartId", "ProductId" },
                keyValues: new object[] { 1001, 1 });

            migrationBuilder.DeleteData(
                table: "CartItems",
                keyColumns: new[] { "CartId", "ProductId" },
                keyValues: new object[] { 1005, 1 });

            migrationBuilder.DeleteData(
                table: "CartItems",
                keyColumns: new[] { "CartId", "ProductId" },
                keyValues: new object[] { 1002, 2 });

            migrationBuilder.DeleteData(
                table: "CartItems",
                keyColumns: new[] { "CartId", "ProductId" },
                keyValues: new object[] { 1006, 2 });

            migrationBuilder.DeleteData(
                table: "CartItems",
                keyColumns: new[] { "CartId", "ProductId" },
                keyValues: new object[] { 1003, 3 });

            migrationBuilder.DeleteData(
                table: "CartItems",
                keyColumns: new[] { "CartId", "ProductId" },
                keyValues: new object[] { 1004, 4 });

            migrationBuilder.DeleteData(
                table: "Orders",
                keyColumn: "Id",
                keyValue: 1001);

            migrationBuilder.DeleteData(
                table: "Orders",
                keyColumn: "Id",
                keyValue: 1002);

            migrationBuilder.DeleteData(
                table: "Orders",
                keyColumn: "Id",
                keyValue: 1003);

            migrationBuilder.DeleteData(
                table: "Orders",
                keyColumn: "Id",
                keyValue: 1004);

            migrationBuilder.DeleteData(
                table: "Orders",
                keyColumn: "Id",
                keyValue: 1005);

            migrationBuilder.DeleteData(
                table: "Orders",
                keyColumn: "Id",
                keyValue: 1006);

            migrationBuilder.DeleteData(
                table: "Carts",
                keyColumn: "Id",
                keyValue: 1001);

            migrationBuilder.DeleteData(
                table: "Carts",
                keyColumn: "Id",
                keyValue: 1002);

            migrationBuilder.DeleteData(
                table: "Carts",
                keyColumn: "Id",
                keyValue: 1003);

            migrationBuilder.DeleteData(
                table: "Carts",
                keyColumn: "Id",
                keyValue: 1004);

            migrationBuilder.DeleteData(
                table: "Carts",
                keyColumn: "Id",
                keyValue: 1005);

            migrationBuilder.DeleteData(
                table: "Carts",
                keyColumn: "Id",
                keyValue: 1006);

            migrationBuilder.DeleteData(
                table: "Employees",
                keyColumn: "Id",
                keyValue: 1001);

            migrationBuilder.DeleteData(
                table: "Payments",
                keyColumn: "Id",
                keyValue: 1001);

            migrationBuilder.DeleteData(
                table: "Payments",
                keyColumn: "Id",
                keyValue: 1002);

            migrationBuilder.DeleteData(
                table: "Payments",
                keyColumn: "Id",
                keyValue: 1003);

            migrationBuilder.DeleteData(
                table: "Payments",
                keyColumn: "Id",
                keyValue: 1004);

            migrationBuilder.DeleteData(
                table: "Payments",
                keyColumn: "Id",
                keyValue: 1005);

            migrationBuilder.DeleteData(
                table: "Payments",
                keyColumn: "Id",
                keyValue: 1006);

            migrationBuilder.DeleteData(
                table: "Accounts",
                keyColumn: "Id",
                keyValue: 1001);

            migrationBuilder.DeleteData(
                table: "Clients",
                keyColumn: "Id",
                keyValue: 5);

            migrationBuilder.DeleteData(
                table: "Clients",
                keyColumn: "Id",
                keyValue: 6);

            migrationBuilder.DeleteData(
                table: "Accounts",
                keyColumn: "Id",
                keyValue: 5);

            migrationBuilder.DeleteData(
                table: "Accounts",
                keyColumn: "Id",
                keyValue: 6);

            migrationBuilder.AddForeignKey(
                name: "FK_ShopIndexVm_Addresses_AddressId",
                table: "ShopIndexVm",
                column: "AddressId",
                principalTable: "Addresses",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
