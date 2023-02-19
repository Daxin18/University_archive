using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Bocian.Migrations
{
    /// <inheritdoc />
    public partial class UpdateFields : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropCheckConstraint(
                name: "CK_Clients_TelephoneNumber_Phone",
                table: "Clients");

            migrationBuilder.RenameColumn(
                name: "TelephoneNumber",
                table: "Clients",
                newName: "PhoneNumber");

            migrationBuilder.RenameIndex(
                name: "IX_Clients_TelephoneNumber",
                table: "Clients",
                newName: "IX_Clients_PhoneNumber");

            migrationBuilder.AddCheckConstraint(
                name: "CK_Clients_PhoneNumber_Phone",
                table: "Clients",
                sql: "\"PhoneNumber\" ~ '^[\\d\\s+-.()]*\\d[\\d\\s+-.()]*((ext\\.|ext|x)\\s*\\d+)?\\s*$'");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropCheckConstraint(
                name: "CK_Clients_PhoneNumber_Phone",
                table: "Clients");

            migrationBuilder.RenameColumn(
                name: "PhoneNumber",
                table: "Clients",
                newName: "TelephoneNumber");

            migrationBuilder.RenameIndex(
                name: "IX_Clients_PhoneNumber",
                table: "Clients",
                newName: "IX_Clients_TelephoneNumber");

            migrationBuilder.AddCheckConstraint(
                name: "CK_Clients_TelephoneNumber_Phone",
                table: "Clients",
                sql: "\"TelephoneNumber\" ~ '^[\\d\\s+-.()]*\\d[\\d\\s+-.()]*((ext\\.|ext|x)\\s*\\d+)?\\s*$'");
        }
    }
}
