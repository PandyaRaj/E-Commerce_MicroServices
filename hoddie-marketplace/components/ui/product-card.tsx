"use client";

import Image from "next/image";
import React from "react";
import { CardBody, CardContainer, CardItem } from "../ui/3d-card";
import Link from "next/link";

interface Product {
  id: string;
  name: string;
  description: string;
  price: number;
  imageSrc: string;
  imageAlt: string;
  detailsLink: string;
}

interface ProductCardProps {
  product: Product;
}

interface CartItem {
  id: string;
  name: string;
  price: number;
  quantity: number;
  imageSrc: string;
}

export function ProductCard({ product }: ProductCardProps): React.ReactElement {
  const {id, name, description, price, imageSrc, imageAlt, detailsLink } = product;

  const addToCart = () => {
    const cart = JSON.parse(localStorage.getItem('cart') || '[]');
    const existingItemIndex = cart.findIndex((item: CartItem) => item.id === id);

    if (existingItemIndex > -1) {
      cart[existingItemIndex].quantity += 1;
    } else {
      cart.push({ id, name, price, quantity: 1, imageSrc });
    }

    localStorage.setItem('cart', JSON.stringify(cart));
    console.log(`Added ${name} to cart`);
  };

  return (
    <CardContainer className="inter-var">
      <CardBody className="relative max-w-[90%] h-auto group/card rounded-xl p-6 border">
        <CardItem translateZ="50" className="text-xl font-bold">
          {name}
        </CardItem>
        <CardItem
          as="p"
          translateZ="60"
          className="text-neutral-500 text-sm max-w-sm mt-2"
        >
          {description}
        </CardItem>
        <CardItem translateZ="100" className="w-full mt-4">
          <Image
            src={imageSrc}
            className="w-full object-cover rounded-xl group-hover/card:shadow-xl"
            alt={imageAlt}
            width={500}
            height={300}
          />
        </CardItem>
        <CardItem
          as="p"
          translateZ="80"
          className="text-2xl font-bold text-emerald-500 mt-4"
        >
          ${price.toFixed(2)}
        </CardItem>
        <div className="flex justify-between items-center mt-6">
          <CardItem
            translateZ={20}
            as="button"
            className="px-4 py-2 rounded-xl text-sm font-bold bg-black text-white hover:bg-gray-800 transition-colors"
            onClick={addToCart}
          >
            Add to Cart
          </CardItem>
          <CardItem
            translateZ={20}
            as={Link}
            href={detailsLink}
            className="px-4 py-2 rounded-xl text-sm font-normal text-blue-500 hover:underline"
          >
            View Details →
          </CardItem>
        </div>
      </CardBody>
    </CardContainer>
  );
}