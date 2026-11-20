using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace SportAppServer.Migrations
{
    /// <inheritdoc />
    public partial class IX_NewsComments_NewsId_UserEmail : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropIndex(
                name: "IX_Comments_NewsId",
                table: "Comments");

            migrationBuilder.CreateIndex(
                name: "IX_NewsComments_NewsId_UserEmail",
                table: "Comments",
                columns: new[] { "NewsId", "Email" });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropIndex(
                name: "IX_NewsComments_NewsId_UserEmail",
                table: "Comments");

            migrationBuilder.CreateIndex(
                name: "IX_Comments_NewsId",
                table: "Comments",
                column: "NewsId");
        }
    }
}
