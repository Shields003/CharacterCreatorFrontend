//This file contains an array of possible magic effects of equipment.

//If the weapon does piercing damage or slashing, it can have tedged Effects. If it does bludgeoning damage, it can have blunt effects.
import meleeWeapons from "./weapons";
import rangedWeapon from "./weapons";



const edgedEffects = [
     {
       name: "Flaming",
       type: "Burning",
   
       damage: "1d6",
       description: "The weapon deals an extra 1d6 fire damage on a hit.",
     },
     {
       name: "Frost",
       type: "Cold",
       damage: "1d6",
       description: "The weapon deals an extra 1d6 cold damage on a hit.",
     },
     {
       name: "Shock",
       type: "Electric",
       damage: "1d6",
       description: "The weapon deals an extra 1d6 lightning damage on a hit.",
     },
     {
       name: "Vicious",
       type: "Bleeding",
       damage: "1d6",
       description: "The weapon deals an extra 1d6 bleeding damage on a hit.",
     },
     {
       name: "Holy",
   
       type: "Radiant",
       damage: "1d6",
       description: "The weapon deals an extra 1d6 radiant damage on a hit.",
     },
     {
       name: "Unholy",
       type: "Necrotic",
       damage: "1d6",
       description: "The weapon deals an extra 1d6 necrotic damage on a hit.",
     },
     {
       name: "Keen",
       type: "Piercing",
       damage: "1d6",
       description: "The weapon deals an extra 1d6 piercing damage on a hit.",
     },
     {
       name: "Brutal",
       type: "Slashing",
       damage: "1d6",
       description: "The weapon deals an extra 1d6 slashing damage on a hit.",
     },
     
   ];

   const bluntEffects = [
     {
          name: "Flaming",
          type: "Burning",
      
          damage: "1d6",
          description: "The weapon deals an extra 1d6 fire damage on a hit.",
        },
        {
          name: "Frost",
          type: "Cold",
          damage: "1d6",
          description: "The weapon deals an extra 1d6 cold damage on a hit.",
        },
        {
          name: "Shock",
          type: "Electric",
          damage: "1d6",
          description: "The weapon deals an extra 1d6 lightning damage on a hit.",
        },
        {
          name: "Holy",
      
          type: "Radiant",
          damage: "1d6",
          description: "The weapon deals an extra 1d6 radiant damage on a hit.",
        },
        {
          name: "Unholy",
          type: "Necrotic",
          damage: "1d6",
          description: "The weapon deals an extra 1d6 necrotic damage on a hit.",
        },
     ];

//arrow function
const getEffect = (type) => {
     if (
       meleeWeapons ||
       rangedWeapon === "Piercing" ||
       type === "Slashing"
     ) {
       return edgedEffects[Math.floor(Math.random() * edgedEffects.length)];
     } else if (meleeWeapons.type || rangedWeapon === "Bludgeoning") {
       return bluntEffects[Math.floor(Math.random() * bluntEffects.length)];
     }
   };
   let effectType = getEffect();
//exporting the function
export default getEffect;