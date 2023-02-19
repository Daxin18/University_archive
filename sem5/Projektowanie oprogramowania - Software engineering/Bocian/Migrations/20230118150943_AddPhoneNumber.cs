using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Bocian.Migrations
{
    /// <inheritdoc />
    public partial class AddPhoneNumber : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
	        migrationBuilder.AddColumn<string>(
                name: "PhoneNumber",
                table: "Shops",
                type: "character varying(16)",
                maxLength: 16,
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "PhoneNumber",
                table: "ShopIndexVm",
                type: "text",
                nullable: false,
                defaultValue: "");

            migrationBuilder.UpdateData(
                table: "Shops",
                keyColumn: "Id",
                keyValue: 1,
                column: "PhoneNumber",
                value: "123 123 123");

            migrationBuilder.UpdateData(
                table: "Shops",
                keyColumn: "Id",
                keyValue: 1001,
                column: "PhoneNumber",
                value: "61 856 37 00");

            migrationBuilder.UpdateData(
                table: "Shops",
                keyColumn: "Id",
                keyValue: 1002,
                column: "PhoneNumber",
                value: "61 856 37 01");

            migrationBuilder.UpdateData(
                table: "Shops",
                keyColumn: "Id",
                keyValue: 1003,
                column: "PhoneNumber",
                value: "61 856 48 22");

            migrationBuilder.UpdateData(
                table: "Shops",
                keyColumn: "Id",
                keyValue: 1004,
                column: "PhoneNumber",
                value: "61 658 27 10");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "PhoneNumber",
                table: "Shops");

            migrationBuilder.DropColumn(
                name: "PhoneNumber",
                table: "ShopIndexVm");

            migrationBuilder.AddColumn<string>(
                name: "PhoneNumber",
                table: "Addresses",
                type: "character varying(16)",
                maxLength: 16,
                nullable: true);

            migrationBuilder.UpdateData(
                table: "Addresses",
                keyColumn: "Id",
                keyValue: 1,
                column: "PhoneNumber",
                value: "123 123 123");

            migrationBuilder.UpdateData(
                table: "Addresses",
                keyColumn: "Id",
                keyValue: 2,
                column: "PhoneNumber",
                value: "989 876 312");

            migrationBuilder.UpdateData(
                table: "Addresses",
                keyColumn: "Id",
                keyValue: 1001,
                column: "PhoneNumber",
                value: "61 856 37 00");

            migrationBuilder.UpdateData(
                table: "Addresses",
                keyColumn: "Id",
                keyValue: 1002,
                column: "PhoneNumber",
                value: "61 856 37 01");

            migrationBuilder.UpdateData(
                table: "Addresses",
                keyColumn: "Id",
                keyValue: 1003,
                column: "PhoneNumber",
                value: "61 856 48 22");

            migrationBuilder.UpdateData(
                table: "Addresses",
                keyColumn: "Id",
                keyValue: 1004,
                column: "PhoneNumber",
                value: "61 658 27 10");
        }
    }
}
