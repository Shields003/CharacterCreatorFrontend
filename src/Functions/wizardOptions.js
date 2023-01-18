//This function is used to generate spells, items, and feats for the wizard class.
//It is called by the wizard class function.


const wizArmor = [
  {
    name: "Bracers of Defense",
    type: "Light Armor",
    ACBonus: "1",
    weight: "1 lb",
    cost: "1,500 gp",
    description: "A pair of bracers that increase your AC by 1.",
  },
  {
    name: "Padded Armor",
    type: "Light Armor",
    ACBonus: "1",
    weight: "8 lb",
    cost: "5 gp",
    description: "A padded suit of armor that increases your AC by 1.",
  },
  {
    name: "Robe",
    type: "Light Armor",
    ACBonus: "1",
    weight: "4 lb",
    cost: "1 gp",
    description: "A robe that increases your AC by 1.",
  },
  {
    name: "Leather Armor",
    type: "Light Armor",
    ACBonus: "2",
    weight: "10 lb",
    cost: "10 gp",
    description: "A suit of leather armor that increases your AC by 2.",
  },
  {
    name: "Studded Leather Armor",
    type: "Light Armor",
    ACBonus: "3",
    weight: "13 lb",
    cost: "45 gp",
    description: "A suit of studded leather armor that increases your AC by 3.",
  },
];

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

     const wizSpells = [
          {
               name: "Acid Splash",
               level: "Cantrip",
               castingTime: "1 action",
               range: "60 feet",
               components: "V, S",
               duration: "Instantaneous",
               description: "You hurl a bubble of acid. Choose one creature within range, or choose two creatures within range that are within 5 feet of each other. A target must succeed on a Dexterity saving throw or take 1d6 acid damage.",
          },
          {
               name: "Chill Touch",
               level: "Cantrip",
               castingTime: "1 action",
               range: "120 feet",
               components: "V, S",
               duration: "1 round",
               description: "You create a ghostly, skeletal hand in the space of a creature within range. Make a ranged spell attack against the creature to assail it with the chill of the grave. On a hit, the target takes 1d8 necrotic damage, and it can't regain hit points until the start of your next turn. Until then, the hand clings to the target. If you hit an undead target, it also has disadvantage on attack rolls against you until the end of your next turn.",
          },
          {
               name: "Dancing Lights",
               level: "Cantrip",
               castingTime: "1 action",
               range: "120 feet",
               components: "V, S",
               duration: "1 minute",
               description: "You create up to four torch-sized lights within range, making them appear as torches, lanterns, or glowing orbs that hover in the air for the duration. You can also combine the four lights into one glowing vaguely humanoid form of Medium size. Whichever form you choose, each light sheds dim light in a 10-foot radius. As a bonus action on your turn, you can move the lights up to 60 feet to a new spot within range.",   
          },

     ];

     


//Create an arrow function that will take in a weapon object and return a string that will be used to display the weapon's information in the DOM.
const createWeaponCard = (weapon) => {
  return `
  <div class="card">
    <h2>${weapon.name}</h2>
    <p>Type: ${weapon.type}</p>
    <p>Damage: ${weapon.damage}</p>
    <p>Weight: ${weapon.weight}</p>
    <p>Cost: ${weapon.cost}</p>
    <p>Description: ${weapon.description}</p>
  </div>
  `;
};

export default createWeaponCard;