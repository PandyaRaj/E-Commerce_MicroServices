// app/cart/page.tsx

"use client";

import React, { useEffect, useState } from 'react';
import Image from 'next/image';
import Link from 'next/link';

interface CartItem {
  id: string;
  name: string;
  price: number;
  quantity: number;
  imageSrc: string;
}

export default function CartPage() {
  const [cartItems, setCartItems] = useState<CartItem[]>([]);

  useEffect(() => {
    // Retrieve cart items from localStorage when the component mounts
    const storedCart = localStorage.getItem('cart');
    if (storedCart) {
      setCartItems(JSON.parse(storedCart));
    }
  }, []);

  const updateQuantity = (id: string, newQuantity: number) => {
    const updatedCart = cartItems.map(item => 
      item.id === id ? { ...item, quantity: Math.max(0, newQuantity) } : item
    ).filter(item => item.quantity > 0);

    setCartItems(updatedCart);
    localStorage.setItem('cart', JSON.stringify(updatedCart));
  };

  const calculateSubtotal = () => {
    return cartItems.reduce((total, item) => total + item.price * item.quantity, 0);
  };

  const calculateTax = (subtotal: number) => {
    return subtotal * 0.13; // 13% HST
  };

  const shippingCost = 13; // $13 shipping charge

  const calculateTotal = () => {
    const subtotal = calculateSubtotal();
    const tax = calculateTax(subtotal);
    return subtotal + tax + shippingCost;
  };

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-2xl font-bold mb-4">Your Cart</h1>
      {cartItems.length === 0 ? (
        <p>Your cart is empty.</p>
      ) : (
        <>
          {cartItems.map((item) => (
            <div key={item.id} className="flex items-center border-b py-4">
              <Image src={item.imageSrc} alt={item.name} width={80} height={80} className="rounded-md" />
              <div className="ml-4 flex-grow">
                <h2 className="font-semibold">{item.name}</h2>
                <p className="text-gray-600">${item.price.toFixed(2)}</p>
              </div>
              <div className="flex items-center">
                <button 
                  onClick={() => updateQuantity(item.id, item.quantity - 1)}
                  className="bg-gray-900 hover:shadow-sm  rounded-sm px-2 py-2 "
                >
                  -
                </button>
                <span className="px-4">{item.quantity}</span>
                <button 
                  onClick={() => updateQuantity(item.id, item.quantity + 1)}
                  className="bg-gray-900 hover:shadow-sm  rounded-sm px-2 py-2 "
                >
                  +
                </button>
              </div>
            </div>
          ))}
          <div className="mt-4 text-right">
            <p className="text-lg">Subtotal: ${calculateSubtotal().toFixed(2)}</p>
            <p className="text-lg">HST (13%): ${calculateTax(calculateSubtotal()).toFixed(2)}</p>
            <p className="text-lg">Shipping: ${shippingCost.toFixed(2)}</p>
            <p className="text-xl font-bold mt-2">Gross Total: ${calculateTotal().toFixed(2)}</p>
          </div>
        </>
      )}
      <Link href="/" className="mt-4 inline-block bg-blue-500 text-white px-4 py-2 rounded">
        Continue Shopping
      </Link>
    </div>
  );
}