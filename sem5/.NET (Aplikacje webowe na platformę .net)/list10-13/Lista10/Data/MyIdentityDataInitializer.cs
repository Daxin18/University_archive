using Microsoft.AspNetCore.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Lista10.Data
{
    public class MyIdentityDataInitializer
    {
        public static void SeedData(UserManager<IdentityUser> userManager, RoleManager<IdentityRole> roleManager)
        {
            SeedRoles(roleManager);
            SeedUsers(userManager);
        }

        // name - poprawny adres email
        // password - min 8 znaków, mała i duża litera, cyfra i znak specjalny
        
        public static void SeedRoles(RoleManager<IdentityRole> roleManager)
        {
            if(!roleManager.RoleExistsAsync("Admin").Result)
            {
                IdentityRole role = new IdentityRole
                {
                    Name = "Admin",
                };
                IdentityResult roleResult = roleManager.CreateAsync(role).Result;
            }
            if (!roleManager.RoleExistsAsync("Client").Result)
            {
                IdentityRole role = new IdentityRole
                {
                    Name = "Client",
                };
                IdentityResult roleResult = roleManager.CreateAsync(role).Result;
            }
        }

        public static void SeedUsers(UserManager<IdentityUser> userManager)
        {
            SeedOneUser(userManager, "normaluser@localhost", "P@ssw0rd");
            SeedOneUser(userManager, "admin@localhost", "P@ssw0rd", "Admin");
            SeedOneUser(userManager, "client@localhost", "P@ssw0rd", "Client");
        }

        public static void SeedOneUser(UserManager<IdentityUser> userManager, string name, string password, string role = null)
        {
            if(userManager.FindByNameAsync(name).Result == null)
            {
                IdentityUser user = new IdentityUser
                {
                    UserName = name, //must be the same as email, otherwise it won't work
                    Email = name
                };

                IdentityResult result = userManager.CreateAsync(user, password).Result;
                if(result.Succeeded && role != null)
                {
                    userManager.AddToRoleAsync(user, role).Wait();
                }
            }
        }

    }
}
