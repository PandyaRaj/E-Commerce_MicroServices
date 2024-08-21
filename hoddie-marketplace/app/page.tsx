"use client";
import Image from "next/image";
import HeroImage from "@/public/hoodies/hero-image.png";
import { motion } from "framer-motion";
import { Spotlight } from "@/components/ui/spotlight";
import { BackgroundGradient } from "@/components/ui/background-gradient";
import { TextGenerateEffect } from "@/components/ui/text-generate-effect";
import { ProductCard } from "@/components/ui/product-card";
import { HoverBorderGradient } from "@/components/ui/hover-border-gradient";

export default function Home() {
  const productData1 = {
    id: "1",
    name: "Exodus Black Hoodie",
    description: "Permium quality synthetic hoodies delivered at your doorstep.",
    price: 179.99,
    imageSrc: "/hoodies/hero-image.png",
    imageAlt: "3D Floating Hoodie",
    detailsLink: "/product/3d-floating-hoodie"
  };

  const productData2 = {
    id: "2",
    name: "Arctic White Hoodie",
    description: "Permium quality synthetic hoodies delivered at your doorstep.",
    price: 99.99,
    imageSrc: "/hoodies/hoodie_white.png",
    imageAlt: "3D Floating Hoodie",
    detailsLink: "/product/3d-floating-hoodie"
  };

  const productData3 = {
    id: "3",
    name: "Sky Blue Hoodie",
    description: "Permium quality synthetic hoodies delivered at your doorstep.",
    price: 69.99,
    imageSrc: "/hoodies/hoodie_blue.png",
    imageAlt: "3D Floating Hoodie",
    detailsLink: "/product/3d-floating-hoodie"
  };

  const productData4 = {
      id: "4",
    name: "Chestnut Brown Hoodie",
    description: "Permium quality synthetic hoodies delivered at your doorstep.",
    price: 59.99,
    imageSrc: "/hoodies/hoodie_brown.png",
    imageAlt: "3D Floating Hoodie",
    detailsLink: "/product/3d-floating-hoodie"
  };


  return (
    <div className="w-full">
      <BackgroundGradient containerClassName="p-2 mt-4 w-[95%] md:w-[75%] mx-auto">
        <div className="p-4 relative bg-background overflow-hidden">
          <Spotlight
            className="-top-20 left-1/4 md:-top-20 md:left-1/2"
            fill="white"
          />

          <div className="grid md:grid-cols-2 justify-center items-center">
            <div className="flex flex-col justify-center items-center">
              <motion.div
                initial={{ opacity: 0.5, y: 20 }}
                whileInView={{ opacity: 1, y: 0 }}
                transition={{
                  delay: 0.2,
                  duration: 0.7,
                  ease: "easeInOut",
                }}
                className="mt-8 relative z-10 w-full"
              >
                <Image src={HeroImage} alt="Hero Image" className="object-cover w-" />
              </motion.div>

              <TextGenerateEffect words={"starts from $59.99"} className="text-md text-center text-shadow-xl hidden md:block" />
            </div>


            <div className="p-4 md:row-start-1 flex flex-col items-center justify-center gap-2">
              <h1 className="text-5xl font-bold tracking-wide text-fuchsia-400">
                Valenciaga
              </h1>

              <TextGenerateEffect words={"next gen fashionable hoodie store."} className="text-xl text-center" />

              <HoverBorderGradient
                containerClassName="rounded-full"
                as="button"
                className="flex items-center cursor-pointer"
              >

                <span>Get busy shopping</span>
              </HoverBorderGradient>
            </div>
          </div>
        </div>
      </BackgroundGradient>

      <motion.h1
        
        initial={{ opacity: 0.3, y: 20 }}
        whileInView={{ opacity: 1, y: 0 }}
        transition={{
          duration: 0.9,
          ease: "easeInOut",
        }}
        className="text-3xl mt-4 font-bold tracking-wide text-fuchsia-400 text-center"
      >
        #Best Sellers
      </motion.h1>
      <div className="grid md:grid-cols-3 gap-2">
        
        {/* <ProductCard product={productData1} /> */}
        <ProductCard product={productData2} />
        <ProductCard product={productData3} />
        <ProductCard product={productData4} />
      </div>
    </div>
  );
}
