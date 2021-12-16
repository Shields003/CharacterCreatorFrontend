// const roll_dice = (number_of_rolls) => {
//   let total = 0;
//   for (let i = 0; i < number_of_rolls; i++) {
//     total += Math.floor(Math.random() * 6) + 1;
//   }
//   return total;
// };

// let hitDice = "1D6";

// const d6 = (number_of_rolls) => {
//   let total = 0;
//   for (let i = 0; i < number_of_rolls; i++) {
//     total += Math.floor(Math.random() * 6) + 1;
//   }
//   return total;
// };
// const hit_dice4 = (number_of_rolls) => {
//   let total = 0;
//   for (let i = 0; i < number_of_rolls; i++) {
//     total += Math.floor(Math.random() * 4) + 1;
//   }
//   return total;
// };
// const hit_dice8 = (number_of_rolls) => {
//   let total = 0;
//   for (let i = 0; i < number_of_rolls; i++) {
//     total += Math.floor(Math.random() * 8) + 1;
//   }
//   return total;
// };
// const hit_dice12 = (number_of_rolls) => {
//   let total = 0;
//   for (let i = 0; i < number_of_rolls; i++) {
//     total += Math.floor(Math.random() * 6) + 1;
//   }
//   return total;
// };
// export default roll_dice;

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

// function makeDie(sides) {
//   const die = function () {
//     return (1 + Math.random() * sides) | 0;
//   };

//   die.times = function (count) {
//     const rolls = [];
//     for (let i = 0; i < count; i++) {
//       rolls.push(this());
//     }
//     return rolls;
//   };

//   return die;
// }

// let dice = {
//   d4: makeDie(4),
//   d6: makeDie(6),
//   d8: makeDie(8),
//   d10: makeDie(10),
//   d12: makeDie(12),
//   d20: makeDie(20),
// };

// export default makeDie;

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
// Map
// const dice = new Map([
//   ["d4", Math.floor(Math.random() * 4) + 1],
//   ["d6", Math.floor(Math.random() * 6) + 1],
//   ["d8", Math.floor(Math.random() * 8) + 1],
//   ["d10", Math.floor(Math.random() * 10) + 1],
//   ["d12", Math.floor(Math.random() * 12) + 1],
//   ["d20", Math.floor(Math.random() * 20) + 1],
//   ["d100", Math.floor(Math.random() * 100) + 1],
// ]);

// export default dice;

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

// const Dice = (props) => {
//   const d4 = (number_of_rolls) => {
//     let total = 0;
//     for (let i = 0; i < number_of_rolls; i++) {
//       total += Math.floor(Math.random() * 4) + 1;
//     }
//     return total;
//   };
//   const d6 = (number_of_rolls) => {
//     let total = 0;
//     for (let i = 0; i < number_of_rolls; i++) {
//       total += Math.floor(Math.random() * 6) + 1;
//     }
//     return total;
//   };
//   const d8 = (number_of_rolls) => {
//     let total = 0;
//     for (let i = 0; i < number_of_rolls; i++) {
//       total += Math.floor(Math.random() * 8) + 1;
//     }
//     return total;
//   };
//   const d10 = (number_of_rolls) => {
//     let total = 0;
//     for (let i = 0; i < number_of_rolls; i++) {
//       total += Math.floor(Math.random() * 10) + 1;
//     }
//     return total;
//   };
//   const d12 = (number_of_rolls) => {
//     let total = 0;
//     for (let i = 0; i < number_of_rolls; i++) {
//       total += Math.floor(Math.random() * 12) + 1;
//     }
//     return total;
//   };
//   const d20 = (number_of_rolls) => {
//     let total = 0;
//     for (let i = 0; i < number_of_rolls; i++) {
//       total += Math.floor(Math.random() * 20) + 1;
//     }
//     return total;
//   };
//   const d100 = (number_of_rolls) => {
//     let total = 0;
//     for (let i = 0; i < number_of_rolls; i++) {
//       total += Math.floor(Math.random() * 100) + 1;
//     }
//     return total;
//   };
// };

// export default Dice;
//
//
