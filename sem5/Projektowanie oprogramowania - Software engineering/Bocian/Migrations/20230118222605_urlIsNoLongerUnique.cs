using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace Bocian.Migrations
{
    /// <inheritdoc />
    public partial class urlIsNoLongerUnique : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropIndex(
                name: "IX_Images_Url",
                table: "Images");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateIndex(
                name: "IX_Images_Url",
                table: "Images",
                column: "Url",
                unique: true);
        }
    }
}
