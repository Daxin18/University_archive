using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Bocian.Migrations
{
    /// <inheritdoc />
    public partial class ManagerShops : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropIndex(
                name: "IX_Shops_ManagerId",
                table: "Shops");

            migrationBuilder.CreateIndex(
                name: "IX_Shops_ManagerId",
                table: "Shops",
                column: "ManagerId");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropIndex(
                name: "IX_Shops_ManagerId",
                table: "Shops");

            migrationBuilder.CreateIndex(
                name: "IX_Shops_ManagerId",
                table: "Shops",
                column: "ManagerId",
                unique: true);
        }
    }
}
