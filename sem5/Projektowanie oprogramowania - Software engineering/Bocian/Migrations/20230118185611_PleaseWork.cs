using Microsoft.EntityFrameworkCore.Migrations;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

#nullable disable

#pragma warning disable CA1814 // Prefer jagged arrays over multidimensional

namespace Bocian.Migrations
{
    /// <inheritdoc />
    public partial class PleaseWork : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "ShopIndexVm");

            migrationBuilder.DropTable(
                name: "AddressVm");

            migrationBuilder.CreateTable(
                name: "ProductCount",
                columns: table => new
                {
                    Id = table.Column<int>(type: "integer", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    ProductId = table.Column<int>(type: "integer", nullable: false),
                    ShopId = table.Column<int>(type: "integer", nullable: false),
                    Quantity = table.Column<int>(type: "integer", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_ProductCount", x => x.Id);
                });

            migrationBuilder.InsertData(
                table: "ProductCount",
                columns: new[] { "Id", "ProductId", "Quantity", "ShopId" },
                values: new object[,]
                {
                    { 1, 1, 55, 1001 },
                    { 2, 2, 0, 1001 },
                    { 3, 3, 215, 1001 },
                    { 4, 4, 1, 1001 }
                });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "ProductCount");

            migrationBuilder.CreateTable(
                name: "AddressVm",
                columns: table => new
                {
                    Id = table.Column<int>(type: "integer", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    ApartmentNumber = table.Column<int>(type: "integer", nullable: true),
                    City = table.Column<string>(type: "text", nullable: false),
                    HouseNumber = table.Column<int>(type: "integer", nullable: false),
                    PostalCode = table.Column<string>(type: "text", nullable: false),
                    Street = table.Column<string>(type: "text", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_AddressVm", x => x.Id);
                });

            migrationBuilder.CreateTable(
                name: "ShopIndexVm",
                columns: table => new
                {
                    Id = table.Column<int>(type: "integer", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    AddressId = table.Column<int>(type: "integer", nullable: false),
                    NumberOfEmployees = table.Column<int>(type: "integer", nullable: false),
                    PhoneNumber = table.Column<string>(type: "text", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_ShopIndexVm", x => x.Id);
                    table.ForeignKey(
                        name: "FK_ShopIndexVm_AddressVm_AddressId",
                        column: x => x.AddressId,
                        principalTable: "AddressVm",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_ShopIndexVm_AddressId",
                table: "ShopIndexVm",
                column: "AddressId");
        }
    }
}
