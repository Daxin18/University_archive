﻿// <auto-generated />
using System;
using Bocian.Data;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

#nullable disable

namespace Bocian.Migrations
{
    [DbContext(typeof(ShopDbContext))]
    [Migration("20230116112025_AddLoyaltyProductPrice")]
    partial class AddLoyaltyProductPrice
    {
        /// <inheritdoc />
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "7.0.1")
                .HasAnnotation("Relational:MaxIdentifierLength", 63);

            NpgsqlModelBuilderExtensions.UseIdentityByDefaultColumns(modelBuilder);

            modelBuilder.Entity("Bocian.Models.Account", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("integer");

                    NpgsqlPropertyBuilderExtensions.UseIdentityByDefaultColumn(b.Property<int>("Id"));

                    b.Property<string>("Email")
                        .IsRequired()
                        .HasMaxLength(255)
                        .HasColumnType("character varying(255)");

                    b.Property<string>("FirstName")
                        .IsRequired()
                        .HasMaxLength(255)
                        .HasColumnType("character varying(255)");

                    b.Property<string>("LastName")
                        .IsRequired()
                        .HasMaxLength(255)
                        .HasColumnType("character varying(255)");

                    b.HasKey("Id");

                    b.HasIndex("Email")
                        .IsUnique();

                    b.ToTable("Accounts", t =>
                        {
                            t.HasCheckConstraint("CK_Accounts_Email_EmailAddress", "\"Email\" ~ '^[^@]+@[^@]+$'");
                        });

                    b.UseTptMappingStrategy();
                });

            modelBuilder.Entity("Bocian.Models.Address", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("integer");

                    NpgsqlPropertyBuilderExtensions.UseIdentityByDefaultColumn(b.Property<int>("Id"));

                    b.Property<int?>("ApartmentNumber")
                        .HasColumnType("integer");

                    b.Property<string>("City")
                        .IsRequired()
                        .HasMaxLength(255)
                        .HasColumnType("character varying(255)");

                    b.Property<int>("HouseNumber")
                        .HasColumnType("integer");

                    b.Property<string>("PostalCode")
                        .IsRequired()
                        .HasMaxLength(6)
                        .HasColumnType("character varying(6)");

                    b.Property<string>("Street")
                        .IsRequired()
                        .HasMaxLength(255)
                        .HasColumnType("character varying(255)");

                    b.HasKey("Id");

                    b.ToTable("Addresses", t =>
                        {
                            t.HasCheckConstraint("CK_Addresses_ApartmentNumber_Range", "\"ApartmentNumber\" >= 1 AND \"ApartmentNumber\" <= 2147483647");

                            t.HasCheckConstraint("CK_Addresses_HouseNumber_Range", "\"HouseNumber\" >= 1 AND \"HouseNumber\" <= 2147483647");

                            t.HasCheckConstraint("CK_Addresses_PostalCode_MinLength", "LENGTH(\"PostalCode\") >= 6");

                            t.HasCheckConstraint("CK_Addresses_PostalCode_RegularExpression", "\"PostalCode\" ~ '^\\d{2}-\\d{3}'");
                        });

                    b.HasData(
                        new
                        {
                            Id = 1,
                            ApartmentNumber = 5,
                            City = "Wrocław",
                            HouseNumber = 18,
                            PostalCode = "58-100",
                            Street = "Słowackiego"
                        });
                });

            modelBuilder.Entity("Bocian.Models.Cart", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("integer");

                    NpgsqlPropertyBuilderExtensions.UseIdentityByDefaultColumn(b.Property<int>("Id"));

                    b.Property<int>("ClientId")
                        .HasColumnType("integer");

                    b.HasKey("Id");

                    b.HasIndex("ClientId");

                    b.ToTable("Carts");

                    b.HasData(
                        new
                        {
                            Id = 1,
                            ClientId = 4
                        });
                });

            modelBuilder.Entity("Bocian.Models.CartItem", b =>
                {
                    b.Property<int>("ProductId")
                        .HasColumnType("integer");

                    b.Property<int>("CartId")
                        .HasColumnType("integer");

                    b.Property<int>("ItemCount")
                        .HasColumnType("integer");

                    b.HasKey("ProductId", "CartId");

                    b.HasIndex("CartId");

                    b.ToTable("CartItems", t =>
                        {
                            t.HasCheckConstraint("CK_CartItems_ItemCount_Range", "\"ItemCount\" >= 1 AND \"ItemCount\" <= 2147483647");
                        });

                    b.HasData(
                        new
                        {
                            ProductId = 1,
                            CartId = 1,
                            ItemCount = 1
                        },
                        new
                        {
                            ProductId = 2,
                            CartId = 1,
                            ItemCount = 3
                        });
                });

            modelBuilder.Entity("Bocian.Models.Category", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("integer");

                    NpgsqlPropertyBuilderExtensions.UseIdentityByDefaultColumn(b.Property<int>("Id"));

                    b.Property<string>("Name")
                        .IsRequired()
                        .HasMaxLength(255)
                        .HasColumnType("character varying(255)");

                    b.HasKey("Id");

                    b.ToTable("Categories");

                    b.HasData(
                        new
                        {
                            Id = 1,
                            Name = "Fast food"
                        },
                        new
                        {
                            Id = 2,
                            Name = "Nabiał"
                        });
                });

            modelBuilder.Entity("Bocian.Models.Complaint", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("integer");

                    NpgsqlPropertyBuilderExtensions.UseIdentityByDefaultColumn(b.Property<int>("Id"));

                    b.Property<DateOnly>("Date")
                        .HasColumnType("date");

                    b.Property<int>("OrderId")
                        .HasColumnType("integer");

                    b.Property<string>("Reason")
                        .IsRequired()
                        .HasColumnType("text");

                    b.HasKey("Id");

                    b.HasIndex("OrderId")
                        .IsUnique();

                    b.ToTable("Complaints");
                });

            modelBuilder.Entity("Bocian.Models.Delivery", b =>
                {
                    b.Property<int>("OrderId")
                        .HasColumnType("integer");

                    b.Property<int>("CourierId")
                        .HasColumnType("integer");

                    b.Property<DateTimeOffset?>("DeliveredAt")
                        .HasColumnType("timestamp with time zone");

                    b.HasKey("OrderId");

                    b.HasIndex("CourierId");

                    b.ToTable("Deliveries");
                });

            modelBuilder.Entity("Bocian.Models.FavoriteProduct", b =>
                {
                    b.Property<int>("ClientId")
                        .HasColumnType("integer");

                    b.Property<int>("ProductId")
                        .HasColumnType("integer");

                    b.Property<DateTimeOffset>("LikedAt")
                        .HasColumnType("timestamp with time zone");

                    b.HasKey("ClientId", "ProductId");

                    b.HasIndex("ProductId");

                    b.ToTable("FavoriteProducts");
                });

            modelBuilder.Entity("Bocian.Models.Image", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("integer");

                    NpgsqlPropertyBuilderExtensions.UseIdentityByDefaultColumn(b.Property<int>("Id"));

                    b.Property<string>("Url")
                        .IsRequired()
                        .HasMaxLength(1000)
                        .HasColumnType("character varying(1000)");

                    b.HasKey("Id");

                    b.HasIndex("Url")
                        .IsUnique();

                    b.ToTable("Images", t =>
                        {
                            t.HasCheckConstraint("CK_Images_Url_Url", "\"Url\" ~ '^(http://|https://|ftp://)'");
                        });

                    b.HasData(
                        new
                        {
                            Id = 1,
                            Url = "https://upload.wikimedia.org/wikipedia/commons/c/c0/Pizza_with_tomatoes.jpg"
                        },
                        new
                        {
                            Id = 2,
                            Url = "https://upload.wikimedia.org/wikipedia/commons/2/28/Polish_%22Zapiekanka%22.jpg"
                        },
                        new
                        {
                            Id = 3,
                            Url = "https://s3.party.pl/newsy/zolty-ser-307663-16_9.jpg"
                        },
                        new
                        {
                            Id = 4,
                            Url = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0e/Milk_glass.jpg/330px-Milk_glass.jpg"
                        });
                });

            modelBuilder.Entity("Bocian.Models.LoyaltyScheme", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("integer");

                    NpgsqlPropertyBuilderExtensions.UseIdentityByDefaultColumn(b.Property<int>("Id"));

                    b.Property<decimal>("DiscountPrice")
                        .HasColumnType("numeric");

                    b.Property<DateOnly?>("EndDate")
                        .HasColumnType("date");

                    b.Property<int>("PointPrice")
                        .HasColumnType("integer");

                    b.Property<int>("ProductId")
                        .HasColumnType("integer");

                    b.Property<DateOnly>("StartDate")
                        .HasColumnType("date");

                    b.HasKey("Id");

                    b.HasIndex("ProductId");

                    b.ToTable("LoyaltySchemes", t =>
                        {
                            t.HasCheckConstraint("CK_LoyaltySchemes_PointPrice_Range", "\"PointPrice\" >= 0 AND \"PointPrice\" <= 2147483647");
                        });

                    b.HasData(
                        new
                        {
                            Id = 1,
                            DiscountPrice = 6.00m,
                            EndDate = new DateOnly(9999, 12, 31),
                            PointPrice = 2500,
                            ProductId = 1,
                            StartDate = new DateOnly(1, 1, 1)
                        },
                        new
                        {
                            Id = 2,
                            DiscountPrice = 18.00m,
                            EndDate = new DateOnly(9999, 12, 31),
                            PointPrice = 3500,
                            ProductId = 3,
                            StartDate = new DateOnly(1, 1, 1)
                        },
                        new
                        {
                            Id = 3,
                            DiscountPrice = 8.00m,
                            EndDate = new DateOnly(2023, 1, 16),
                            PointPrice = 500,
                            ProductId = 2,
                            StartDate = new DateOnly(1, 1, 1)
                        });
                });

            modelBuilder.Entity("Bocian.Models.Order", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("integer");

                    NpgsqlPropertyBuilderExtensions.UseIdentityByDefaultColumn(b.Property<int>("Id"));

                    b.Property<int>("AddressId")
                        .HasColumnType("integer");

                    b.Property<int>("CartId")
                        .HasColumnType("integer");

                    b.Property<int>("ClientId")
                        .HasColumnType("integer");

                    b.Property<DateTimeOffset>("OrderedAt")
                        .HasColumnType("timestamp with time zone");

                    b.Property<int>("PaymentId")
                        .HasColumnType("integer");

                    b.Property<int>("Status")
                        .HasColumnType("integer");

                    b.HasKey("Id");

                    b.HasIndex("AddressId");

                    b.HasIndex("CartId")
                        .IsUnique();

                    b.HasIndex("ClientId");

                    b.HasIndex("PaymentId")
                        .IsUnique();

                    b.ToTable("Orders", t =>
                        {
                            t.HasCheckConstraint("CK_Orders_Status_Enum", "\"Status\" IN (0, 1, 2, 3, 4, 5, 6)");
                        });

                    b.HasData(
                        new
                        {
                            Id = 1,
                            AddressId = 1,
                            CartId = 1,
                            ClientId = 4,
                            OrderedAt = new DateTimeOffset(new DateTime(2023, 1, 12, 9, 37, 10, 0, DateTimeKind.Unspecified), new TimeSpan(0, 1, 0, 0, 0)),
                            PaymentId = 1,
                            Status = 0
                        });
                });

            modelBuilder.Entity("Bocian.Models.Payment", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("integer");

                    NpgsqlPropertyBuilderExtensions.UseIdentityByDefaultColumn(b.Property<int>("Id"));

                    b.Property<DateTimeOffset?>("PaymentTime")
                        .HasColumnType("timestamp with time zone");

                    b.Property<int>("PaymentType")
                        .HasColumnType("integer");

                    b.Property<decimal>("TotalPrice")
                        .HasPrecision(18, 2)
                        .HasColumnType("numeric(18,2)");

                    b.HasKey("Id");

                    b.ToTable("Payments", t =>
                        {
                            t.HasCheckConstraint("CK_Payments_PaymentType_Enum", "\"PaymentType\" IN (0, 1, 2)");
                        });

                    b.HasData(
                        new
                        {
                            Id = 1,
                            PaymentType = 2,
                            TotalPrice = 0m
                        });
                });

            modelBuilder.Entity("Bocian.Models.Product", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("integer");

                    NpgsqlPropertyBuilderExtensions.UseIdentityByDefaultColumn(b.Property<int>("Id"));

                    b.Property<int>("CategoryId")
                        .HasColumnType("integer");

                    b.Property<string>("Description")
                        .IsRequired()
                        .HasMaxLength(1000)
                        .HasColumnType("character varying(1000)");

                    b.Property<int>("ImageId")
                        .HasColumnType("integer");

                    b.Property<string>("Name")
                        .IsRequired()
                        .HasMaxLength(255)
                        .HasColumnType("character varying(255)");

                    b.Property<bool>("OnlyForAdults")
                        .HasColumnType("boolean");

                    b.Property<decimal>("Price")
                        .HasPrecision(18, 2)
                        .HasColumnType("numeric(18,2)");

                    b.Property<double>("Weight")
                        .HasColumnType("double precision");

                    b.HasKey("Id");

                    b.HasIndex("CategoryId");

                    b.HasIndex("ImageId");

                    b.HasIndex("Name")
                        .IsUnique();

                    b.ToTable("Products", t =>
                        {
                            t.HasCheckConstraint("CK_Products_Weight_Range", "\"Weight\" >= 0.0 AND \"Weight\" <= 'Infinity'");
                        });

                    b.HasData(
                        new
                        {
                            Id = 1,
                            CategoryId = 1,
                            Description = "Potrawa kuchni włoskiej, obecnie szeroko rozpowszechniona na całym świecie.",
                            ImageId = 1,
                            Name = "Pizza",
                            OnlyForAdults = false,
                            Price = 13.00m,
                            Weight = 0.29999999999999999
                        },
                        new
                        {
                            Id = 2,
                            CategoryId = 1,
                            Description = "Polski fast food, powstały w latach 70. XX wieku, w okresie Polskiej Rzeczypospolitej Ludowej.",
                            ImageId = 2,
                            Name = "Zapiekanka",
                            OnlyForAdults = false,
                            Price = 7.00m,
                            Weight = 0.23000000000000001
                        },
                        new
                        {
                            Id = 3,
                            CategoryId = 2,
                            Description = "Produkt spożywczy wytwarzany poprzez wytrącenie z mleka tłuszczu i białka w postaci skrzepu, który zostaje poddany dalszej obróbce.",
                            ImageId = 3,
                            Name = "Ser",
                            OnlyForAdults = false,
                            Price = 23.00m,
                            Weight = 1.0
                        },
                        new
                        {
                            Id = 4,
                            CategoryId = 2,
                            Description = "Mleko ma liczne wartości odżywcze - w jego skład wchodzą witaminy i pierwiastki mineralne, odpowiedzialne za prawidłowe funkcjonowanie organizmu.",
                            ImageId = 4,
                            Name = "Mleko",
                            OnlyForAdults = false,
                            Price = 1.50m,
                            Weight = 1.0029999999999999
                        });
                });

            modelBuilder.Entity("Bocian.Models.Shop", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("integer");

                    NpgsqlPropertyBuilderExtensions.UseIdentityByDefaultColumn(b.Property<int>("Id"));

                    b.Property<int>("AddressId")
                        .HasColumnType("integer");

                    b.Property<int>("ManagerId")
                        .HasColumnType("integer");

                    b.HasKey("Id");

                    b.HasIndex("AddressId");

                    b.HasIndex("ManagerId");

                    b.ToTable("Shops");
                });

            modelBuilder.Entity("Bocian.Models.TransportationType", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("integer");

                    NpgsqlPropertyBuilderExtensions.UseIdentityByDefaultColumn(b.Property<int>("Id"));

                    b.Property<string>("Name")
                        .IsRequired()
                        .HasMaxLength(256)
                        .HasColumnType("character varying(256)");

                    b.HasKey("Id");

                    b.HasIndex("Name")
                        .IsUnique();

                    b.ToTable("TransportationTypes", t =>
                        {
                            t.HasCheckConstraint("CK_TransportationTypes_Name_MinLength", "LENGTH(\"Name\") >= 1");
                        });

                    b.HasData(
                        new
                        {
                            Id = 1,
                            Name = "Skuter"
                        });
                });

            modelBuilder.Entity("Bocian.Models.Client", b =>
                {
                    b.HasBaseType("Bocian.Models.Account");

                    b.Property<int>("OwnedPoints")
                        .HasColumnType("integer");

                    b.Property<string>("PhoneNumber")
                        .HasMaxLength(16)
                        .HasColumnType("character varying(16)");

                    b.Property<int>("SpentPoints")
                        .HasColumnType("integer");

                    b.HasIndex("PhoneNumber")
                        .IsUnique();

                    b.ToTable("Clients", t =>
                        {
                            t.HasCheckConstraint("CK_Accounts_Email_EmailAddress", "\"Email\" ~ '^[^@]+@[^@]+$'");

                            t.HasCheckConstraint("CK_Clients_OwnedPoints_Range", "\"OwnedPoints\" >= 0 AND \"OwnedPoints\" <= 2147483647");

                            t.HasCheckConstraint("CK_Clients_PhoneNumber_Phone", "\"PhoneNumber\" ~ '^[\\d\\s+-.()]*\\d[\\d\\s+-.()]*((ext\\.|ext|x)\\s*\\d+)?\\s*$'");

                            t.HasCheckConstraint("CK_Clients_SpentPoints_Range", "\"SpentPoints\" >= 0 AND \"SpentPoints\" <= 2147483647");
                        });

                    b.HasData(
                        new
                        {
                            Id = 4,
                            Email = "klient@ebocian.pl",
                            FirstName = "Kamil",
                            LastName = "Ciągło",
                            OwnedPoints = 0,
                            SpentPoints = 0
                        });
                });

            modelBuilder.Entity("Bocian.Models.Courier", b =>
                {
                    b.HasBaseType("Bocian.Models.Account");

                    b.Property<TimeOnly>("AvailableFrom")
                        .HasColumnType("time without time zone");

                    b.Property<TimeOnly>("AvailableTo")
                        .HasColumnType("time without time zone");

                    b.Property<int>("TransportationTypeId")
                        .HasColumnType("integer");

                    b.HasIndex("TransportationTypeId");

                    b.ToTable("Couriers", t =>
                        {
                            t.HasCheckConstraint("CK_Accounts_Email_EmailAddress", "\"Email\" ~ '^[^@]+@[^@]+$'");
                        });

                    b.HasData(
                        new
                        {
                            Id = 2,
                            Email = "kurier@ebocian.pl",
                            FirstName = "Tomasz",
                            LastName = "Chojnacki",
                            AvailableFrom = new TimeOnly(8, 0, 0),
                            AvailableTo = new TimeOnly(15, 0, 0),
                            TransportationTypeId = 1
                        });
                });

            modelBuilder.Entity("Bocian.Models.Employee", b =>
                {
                    b.HasBaseType("Bocian.Models.Account");

                    b.ToTable("Employees", t =>
                        {
                            t.HasCheckConstraint("CK_Accounts_Email_EmailAddress", "\"Email\" ~ '^[^@]+@[^@]+$'");
                        });

                    b.HasData(
                        new
                        {
                            Id = 1,
                            Email = "pracownik@ebocian.pl",
                            FirstName = "Tomasz",
                            LastName = "Chojnacki"
                        });
                });

            modelBuilder.Entity("Bocian.Models.Manager", b =>
                {
                    b.HasBaseType("Bocian.Models.Employee");

                    b.ToTable("Managers", t =>
                        {
                            t.HasCheckConstraint("CK_Accounts_Email_EmailAddress", "\"Email\" ~ '^[^@]+@[^@]+$'");
                        });

                    b.HasData(
                        new
                        {
                            Id = 3,
                            Email = "kierownik@ebocian.pl",
                            FirstName = "Jakub",
                            LastName = "Zehner"
                        });
                });

            modelBuilder.Entity("Bocian.Models.Cart", b =>
                {
                    b.HasOne("Bocian.Models.Client", "Client")
                        .WithMany("Carts")
                        .HasForeignKey("ClientId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Client");
                });

            modelBuilder.Entity("Bocian.Models.CartItem", b =>
                {
                    b.HasOne("Bocian.Models.Cart", "Cart")
                        .WithMany("Items")
                        .HasForeignKey("CartId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("Bocian.Models.Product", "Product")
                        .WithMany()
                        .HasForeignKey("ProductId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Cart");

                    b.Navigation("Product");
                });

            modelBuilder.Entity("Bocian.Models.Complaint", b =>
                {
                    b.HasOne("Bocian.Models.Order", "Order")
                        .WithOne("Complaint")
                        .HasForeignKey("Bocian.Models.Complaint", "OrderId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Order");
                });

            modelBuilder.Entity("Bocian.Models.Delivery", b =>
                {
                    b.HasOne("Bocian.Models.Courier", "Courier")
                        .WithMany("Deliveries")
                        .HasForeignKey("CourierId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("Bocian.Models.Order", "Order")
                        .WithOne("Delivery")
                        .HasForeignKey("Bocian.Models.Delivery", "OrderId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Courier");

                    b.Navigation("Order");
                });

            modelBuilder.Entity("Bocian.Models.FavoriteProduct", b =>
                {
                    b.HasOne("Bocian.Models.Client", "Client")
                        .WithMany("FavoriteProducts")
                        .HasForeignKey("ClientId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("Bocian.Models.Product", "Product")
                        .WithMany()
                        .HasForeignKey("ProductId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Client");

                    b.Navigation("Product");
                });

            modelBuilder.Entity("Bocian.Models.LoyaltyScheme", b =>
                {
                    b.HasOne("Bocian.Models.Product", "Product")
                        .WithMany("LoyaltySchemes")
                        .HasForeignKey("ProductId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Product");
                });

            modelBuilder.Entity("Bocian.Models.Order", b =>
                {
                    b.HasOne("Bocian.Models.Address", "Address")
                        .WithMany()
                        .HasForeignKey("AddressId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("Bocian.Models.Cart", "Cart")
                        .WithOne("Order")
                        .HasForeignKey("Bocian.Models.Order", "CartId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("Bocian.Models.Client", "Client")
                        .WithMany("Orders")
                        .HasForeignKey("ClientId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("Bocian.Models.Payment", "Payment")
                        .WithOne("Order")
                        .HasForeignKey("Bocian.Models.Order", "PaymentId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Address");

                    b.Navigation("Cart");

                    b.Navigation("Client");

                    b.Navigation("Payment");
                });

            modelBuilder.Entity("Bocian.Models.Product", b =>
                {
                    b.HasOne("Bocian.Models.Category", "Category")
                        .WithMany("Products")
                        .HasForeignKey("CategoryId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("Bocian.Models.Image", "Image")
                        .WithMany()
                        .HasForeignKey("ImageId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Category");

                    b.Navigation("Image");
                });

            modelBuilder.Entity("Bocian.Models.Shop", b =>
                {
                    b.HasOne("Bocian.Models.Address", "Address")
                        .WithMany()
                        .HasForeignKey("AddressId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("Bocian.Models.Manager", "Manager")
                        .WithMany("Shops")
                        .HasForeignKey("ManagerId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Address");

                    b.Navigation("Manager");
                });

            modelBuilder.Entity("Bocian.Models.Client", b =>
                {
                    b.HasOne("Bocian.Models.Account", null)
                        .WithOne()
                        .HasForeignKey("Bocian.Models.Client", "Id")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("Bocian.Models.Courier", b =>
                {
                    b.HasOne("Bocian.Models.Account", null)
                        .WithOne()
                        .HasForeignKey("Bocian.Models.Courier", "Id")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("Bocian.Models.TransportationType", "TransportationType")
                        .WithMany()
                        .HasForeignKey("TransportationTypeId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("TransportationType");
                });

            modelBuilder.Entity("Bocian.Models.Employee", b =>
                {
                    b.HasOne("Bocian.Models.Account", null)
                        .WithOne()
                        .HasForeignKey("Bocian.Models.Employee", "Id")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("Bocian.Models.Manager", b =>
                {
                    b.HasOne("Bocian.Models.Employee", null)
                        .WithOne()
                        .HasForeignKey("Bocian.Models.Manager", "Id")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("Bocian.Models.Cart", b =>
                {
                    b.Navigation("Items");

                    b.Navigation("Order");
                });

            modelBuilder.Entity("Bocian.Models.Category", b =>
                {
                    b.Navigation("Products");
                });

            modelBuilder.Entity("Bocian.Models.Order", b =>
                {
                    b.Navigation("Complaint");

                    b.Navigation("Delivery");
                });

            modelBuilder.Entity("Bocian.Models.Payment", b =>
                {
                    b.Navigation("Order");
                });

            modelBuilder.Entity("Bocian.Models.Product", b =>
                {
                    b.Navigation("LoyaltySchemes");
                });

            modelBuilder.Entity("Bocian.Models.Client", b =>
                {
                    b.Navigation("Carts");

                    b.Navigation("FavoriteProducts");

                    b.Navigation("Orders");
                });

            modelBuilder.Entity("Bocian.Models.Courier", b =>
                {
                    b.Navigation("Deliveries");
                });

            modelBuilder.Entity("Bocian.Models.Manager", b =>
                {
                    b.Navigation("Shops");
                });
#pragma warning restore 612, 618
        }
    }
}
