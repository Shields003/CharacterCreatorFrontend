import React from "react";

export default function dice() {
  const d4 = (number_of_rolls) => {
    let total = 0;
    for (let i = 0; i < number_of_rolls; i++) {
      total += Math.floor(Math.random() * 4) + 1;
    }
    return total;
  };
  const d6 = (number_of_rolls) => {
    let total = 0;
    for (let i = 0; i < number_of_rolls; i++) {
      total += Math.floor(Math.random() * 6) + 1;
    }
    return total;
  };
  const d8 = (number_of_rolls) => {
    let total = 0;
    for (let i = 0; i < number_of_rolls; i++) {
      total += Math.floor(Math.random() * 8) + 1;
    }
    return total;
  };
  const d10 = (number_of_rolls) => {
    let total = 0;
    for (let i = 0; i < number_of_rolls; i++) {
      total += Math.floor(Math.random() * 10) + 1;
    }
    return total;
  };
  const d12 = (number_of_rolls) => {
    let total = 0;
    for (let i = 0; i < number_of_rolls; i++) {
      total += Math.floor(Math.random() * 12) + 1;
    }
    return total;
  };
  const d20 = (number_of_rolls) => {
    let total = 0;
    for (let i = 0; i < number_of_rolls; i++) {
      total += Math.floor(Math.random() * 20) + 1;
    }
    return total;
  };
  const d100 = (number_of_rolls) => {
    let total = 0;
    for (let i = 0; i < number_of_rolls; i++) {
      total += Math.floor(Math.random() * 100) + 1;
    }
    return total;
  };
}
