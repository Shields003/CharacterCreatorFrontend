const get_random_main = () => {
  const mainWeapon = [
    "Long-Sword",
    "Staff",
    "Long-Bow",
    "Dagger",
    "Battle-Axe",
    "Short-Sword",
    "War-Hammer",
  ];
  return mainWeapon[Math.floor(Math.random() * 7)];
};
const get_start_equipment = () => {
  const startEquip = ["Bed-Roll, 5 Torches, 40ft Rope, Flint, Water Skin, "];
  return startEquip[Math.floor(Math.random(1))];
};
const get_start_equipment2 = () => {
  const startEquip2 = ["Hooded Lantern, 5 Days Rations."];
  return startEquip2[Math.floor(Math.random(1))];
};
const get_random_off = () => {
  const offWeapon = [
    "Long-Sword",
    "Staff",
    "Long-Bow",
    "Dagger",
    "Battle-Axe",
    "Short-Sword",
    "War-Hammer",
  ];
  return offWeapon[Math.floor(Math.random() * 7)];
};
const get_random_skill = () => {
  const randSkill = [
    "Athletics",
    "Pick Locks",
    "Persuasion",
    "Acrobatics",
    "Perception",
    "Grappling",
    "History",
  ];
  return randSkill[Math.floor(Math.random() * 7)];
};
const get_random_spell = () => {
  const randSpell = [
    "Magic-Missile",
    "Detect Magic",
    "Minor Healing",
    "Flame Strike",
    "Dispel Magic",
    "Lighting Bolt",
    "Chilling Grasp",
  ];
  return randSpell[Math.floor(Math.random() * 7)];
};

//Stats random dice rolls 2-6

const roll_dice = (number_of_rolls) => {
  let total = 0;
  for (let i = 0; i < number_of_rolls; i++) {
    total += Math.floor(Math.random() * 6) + 1;
  }
  return total;
};

let hitDice = "1D6";

const hit_dice = (number_of_rolls) => {
  let total = 0;
  for (let i = 0; i < number_of_rolls; i++) {
    total += Math.floor(Math.random() * 6) + 1;
  }
  return total;
};
const hit_dice4 = (number_of_rolls) => {
  let total = 0;
  for (let i = 0; i < number_of_rolls; i++) {
    total += Math.floor(Math.random() * 4) + 1;
  }
  return total;
};
const hit_dice8 = (number_of_rolls) => {
  let total = 0;
  for (let i = 0; i < number_of_rolls; i++) {
    total += Math.floor(Math.random() * 8) + 1;
  }
  return total;
};
const hit_dice12 = (number_of_rolls) => {
  let total = 0;
  for (let i = 0; i < number_of_rolls; i++) {
    total += Math.floor(Math.random() * 6) + 1;
  }
  return total;
};

//gender
const get_random_sex = () => {
  const gender = ["Male", "Female"];

  return gender[Math.floor(Math.random() * 2)];
};

//get Alignment
const get_random_alignment = () => {
  return [
    "Lawful Good",
    "Chaotic Good",
    "Neutral Good",
    "Lawful Neutral",
    "Chaotic Neutral",
    "Lawful Evil",
    "Chaotic Evil",
    "Neutral Evil",
  ][Math.floor(Math.random() * 7)];
};

//Get Race
const get_random_race = () => {
  return [
    "Human",
    "Half-Elf",
    "Elf",
    "Half-Orc",
    "Gnome",
    "Dwarf",
    "Drow",
    "Tiefling",
  ][Math.floor(Math.random() * 8)];
};

//Get Class
// function that returns a randomized DnD Class
const get_random_class = () => {
  const _class = [
    "Druid",
    "Cleric",
    "Fighter",
    "Rogue",
    "Ranger",
    "Wizard",
    "Monk",
    "Sorcerer",
    "Palladin",
    "Barbarian",
    "Bard",
    "Warlock",
  ];
  return _class[Math.floor(Math.random() * 12)];
};

//Hair and eye colors
const get_random_hair = () => {
  const hairColor = [
    "Black",
    "Brown",
    "Red",
    "Blonde",
    "White",
    "Grey",
    "Bald",
  ];
  return hairColor[Math.floor(Math.random() * 7)];
};

const get_random_eye = () => {
  const eyeColor = ["Black", "Brown", "Green", "Hazel", "Blue", "Grey"];

  return eyeColor[Math.floor(Math.random() * 6)];
};

//Gets base hieght at weight
const get_random_height = () => {
  return Math.floor(Math.random() * 8) + 38;
};
const get_random_weight = () => {
  return Math.floor(Math.random() * 40) + 80;
};

//generates a random level between 1 and 20
const get_random_level = () => {
  return Math.floor(Math.random() * 20) + 1;
};
//Age date
const get_random_age = () => {
  return Math.floor(Math.random() * 17) + 1;
};

//Bonus stats declaration

const get_str_bonus = (strength) => {
  let strBonus = 0;
  if (strength === 13 || strength === 14) {
    strBonus = 1;
  } else if (strength === 15 || strength === 16) {
    strBonus = 2;
  } else if (strength === 17 || strength === 18) {
    strBonus = 3;
  } else if (strength === 19 || strength === 20) {
    strBonus = 4;
  } else if (strength > 20) {
    strBonus = 5;
  }
  return strBonus;
};

const get_dex_bonus = (dexterity) => {
  let dexBonus = 0;
  if (dexterity === 13 || dexterity === 14) {
    dexBonus = 1;
  } else if (dexterity === 15 || dexterity === 16) {
    dexBonus = 2;
  } else if (dexterity === 17 || dexterity === 18) {
    dexBonus = 3;
  } else if (dexterity === 19 || dexterity === 20) {
    dexBonus = 4;
  } else if (dexterity > 20) {
    dexBonus = 5;
  }
  return dexBonus;
};

const get_con_bonus = (constitution) => {
  let conBonus = 0;
  if (constitution === 13 || constitution === 14) {
    conBonus = 1;
  } else if (constitution === 15 || constitution === 16) {
    conBonus = 2;
  } else if (constitution === 17 || constitution === 18) {
    conBonus = 3;
  } else if (constitution === 19 || constitution === 20) {
    conBonus = 4;
  } else if (constitution > 20) {
    conBonus = 5;
  }
  return conBonus;
};

const get_int_bonus = (intelligence) => {
  let intBonus = 0;
  if (intelligence === 13 || intelligence === 14) {
    intBonus = 1;
  } else if (intelligence === 15 || intelligence === 16) {
    intBonus = 2;
  } else if (intelligence === 17 || intelligence === 18) {
    intBonus = 3;
  } else if (intelligence === 19 || intelligence === 20) {
    intBonus = 4;
  } else if (intelligence > 20) {
    intBonus = 5;
  }
  return intBonus;
};

const get_wis_bonus = (wisdom) => {
  let wisBonus = 0;
  if (wisdom === 13 || wisdom === 14) {
    wisBonus = 1;
  } else if (wisdom === 15 || wisdom === 16) {
    wisBonus = 2;
  } else if (wisdom === 17 || wisdom === 18) {
    wisBonus = 3;
  } else if (wisdom === 19 || wisdom === 20) {
    wisBonus = 4;
  } else if (wisdom > 20) {
    wisBonus = 5;
  }
  return wisBonus;
};

const get_cha_bonus = (charisma) => {
  let chaBonus = 0;
  if (charisma === 13 || charisma === 14) {
    chaBonus = 1;
  } else if (charisma === 15 || charisma === 16) {
    chaBonus = 2;
  } else if (charisma === 17 || charisma === 18) {
    chaBonus = 3;
  } else if (charisma === 19 || charisma === 20) {
    chaBonus = 4;
  } else if (charisma > 20) {
    chaBonus = 5;
  }
  return chaBonus;
};

//Rolls for stats and compiles char data
const get_character_choices = (count) => {
  const choices = [];
  for (let i = 0; i < count; i++) {
    const level = get_random_level();
    const expPoints = level * 2500 - Math.floor(Math.random() * 2499);
    const new_character = {
      race: get_random_race(),
      _class: get_random_class(),
      level,
      alignment: get_random_alignment(),
      strength: roll_dice(3),
      liftBend: roll_dice(4),
      carryWeight: roll_dice(),
      dexterity: roll_dice(3),
      constitution: roll_dice(3),
      intelligence: roll_dice(3),
      wisdom: roll_dice(3),
      charisma: roll_dice(3),
      charHeight: get_random_height(),
      charWeight: get_random_weight(),
      hairColor: get_random_hair(),
      eyeColor: get_random_eye(),
      hitPoints: hit_dice(level),
      mainWeapon: get_random_main(),
      randSpell: get_random_spell(),
      randSkill: get_random_skill(),
      startEquip: get_start_equipment(),
      startEquip2: get_start_equipment2(),
      expPoints,
      dodge: 11,
      hitDice: hitDice,
      investigation: 11,
      skillBonus: 0,
      perception: 11,
      insight: 11,
      charm: 11,
      intimidate: 11,
      armorClass: 11,
      thaco: 11,
      speed: 30,
      saveThrow: 11,
      initiative: 0,
      gender: get_random_sex(),
      age: get_random_age(),
    };
    console.log(new_character);
    choices.push(new_character);
  }

  //This adjusts stat scores for race
  const bonus_choices = choices.map((character, j) => {
    const new_character = { ...character };
    //RACE Bonus

    if (new_character.gender === "Female") {
      new_character.charWeight -= Math.floor(Math.random() * 25) + 1;
      new_character.charHeight -= Math.floor(Math.random() * 5) + 1;
    }

    if (new_character.race === "Elf") {
      new_character.dexterity += 2;
      new_character.charHeight += Math.floor(Math.random() * 8) + 18;
      new_character.charWeight += Math.floor(Math.random() * 10) + 50;
      new_character.age +=
        Math.floor(Math.random() * 100) + 23 + new_character.level;
    }
    if (new_character.race === "Half-Orc") {
      new_character.strength += 3;

      new_character.constitution += 1;
      new_character.intelligence -= 2;
      new_character.charHeight += Math.floor(Math.random() * 7) + 32;
      new_character.charWeight +=
        Math.floor(Math.random() * 10) + new_character.charHeight * 1.5;
      new_character.age =
        Math.floor(Math.random() * 10) + 16 + new_character.level;
    }
    if (new_character.race === "Dwarf") {
      new_character.strength += 1;
      new_character.constitution += 1;
      new_character.charHeight += Math.floor(Math.random() * 10) + 8;
      new_character.charWeight += Math.floor(Math.random() * 10) + 40;
      new_character.age =
        Math.floor(Math.random() * 100) + 35 + new_character.level;
    }
    if (new_character.race === "Half-Elf") {
      new_character.dexterity += 1;
      new_character.charisma += 1;
      new_character.charHeight += Math.floor(Math.random() * 10) + 20;
      new_character.charWeight += Math.floor(Math.random() * 25) + 50;
      new_character.age =
        Math.floor(Math.random() * 35) + 19 + new_character.level;
    }
    if (new_character.race === "Gnome") {
      new_character.intelligence += 3;
      new_character.dexterity += 2;
      new_character.strength = hit_dice12(2);
    }
    if (new_character.race === "Drow") {
      new_character.charisma -= 1;
      new_character.dexterity += 2;
      new_character.constitution += 1;
      new_character.charWeight -= 15;
      new_character.charHeight += Math.floor(Math.random() * 7) + 20;
      new_character.charWeight +=
        Math.floor(Math.random() * 3) + new_character.charHeight;
      new_character.age =
        Math.floor(Math.random() * 100) + 22 + new_character.level;
    }
    if (new_character.race === "Tiefling") {
      new_character.intelligence += 1;
      new_character.constitution += 1;
      new_character.charHeight += Math.floor(Math.random() * 8) + 26;
      new_character.charWeight += Math.floor(Math.random() * 75) + 50;
      new_character.age = Math.floor(Math.random() * 60) + 17;
    }
    if (new_character.race === "Human") {
      new_character.strength += Math.floor(Math.random() * 6) - 2;
      new_character.dexterity += Math.floor(Math.random() * 6) - 2;
      new_character.constitution += Math.floor(Math.random() * 6) - 2;
      new_character.intelligence += Math.floor(Math.random() * 6) - 2;
      new_character.wisdom += Math.floor(Math.random() * 6) - 2;
      new_character.charisma += Math.floor(Math.random() * 6) - 2;
      new_character.charHeight += Math.floor(Math.random() * 9) + 30;
      new_character.charWeight += Math.floor(Math.random() * 70) + 40;
      new_character.age =
        Math.floor(Math.random() * 10) + 17 + new_character.level;
    }

    // Adjusts Class and level Bonuses
    if (new_character._class === "Fighter") {
      new_character.strength = Math.floor(
        (new_character.strength = roll_dice(3) + 3)
      );
      new_character.constitution += Math.floor(new_character.level / 5) + 1;
      new_character.strength += Math.floor(new_character.level / 4) + 1;
      new_character.hitDice = "2d4";

      new_character.hitPoints = hit_dice4(new_character.level) * 2;
    }

    if (new_character._class === "Rogue") {
      new_character.dexterity = Math.floor(
        (new_character.dexterity = roll_dice(3) + 3)
      );
      new_character.dexterity += Math.floor(new_character.level / 4) + 1;
      new_character.charisma += Math.floor(new_character.level / 5) + 1;
      new_character.hitDice = "1d6";
      new_character.hitPoints = hit_dice(new_character.level);
    }
    if (new_character._class === "Wizard") {
      new_character.intelligence = Math.floor(
        (new_character.intelligence = roll_dice(3) + 3)
      );
      new_character.intelligence += Math.floor(new_character.level / 4) + 1;
      new_character.wisdom += Math.floor(new_character.level / 5) + 1;
      new_character.hitDice = "1d4";
      new_character.hitPoints = hit_dice4(new_character.level);
    }
    if (new_character._class === "Sorcerer") {
      new_character.intelligence = Math.floor(
        (new_character.intelligence = roll_dice(3) + 3)
      );
      new_character.intelligence += Math.floor(new_character.level / 4) + 1;
      new_character.constitution += Math.floor(new_character.level / 5) + 1;
      new_character.hitDice = "1d4";
      new_character.hitPoints = hit_dice4(new_character.level);
    }
    if (new_character._class === "Warlock") {
      new_character.intelligence = Math.floor(
        (new_character.intelligence = roll_dice(3) + 3)
      );
      new_character.intelligence += Math.floor(new_character.level / 4) + 1;
      new_character.constitution += Math.floor(new_character.level / 5) + 1;
      new_character.hitDice = "1d4";
      new_character.hitPoints = hit_dice4(new_character.level);
    }
    if (new_character._class === "Ranger") {
      new_character.dexterity = Math.floor(
        (new_character.dexterity = roll_dice(3) + 3)
      );
      new_character.dexterity += Math.floor(new_character.level / 4) + 1;
      new_character.wisdom += Math.floor(new_character.level / 5) + 1;
      new_character.hitDice = "1d6";
      new_character.hitPoints = hit_dice(new_character.level);
    }
    if (new_character._class === "Monk") {
      new_character.dexterity = Math.floor(
        (new_character.dexterity = roll_dice(3) + 3)
      );
      new_character.dexterity += Math.floor(new_character.level / 4) + 1;
      new_character.constitution += Math.floor(new_character.level / 5) + 1;
      new_character.hitDice = "2d4";
      new_character.hitPoints = hit_dice4(new_character.level) * 2;
    }
    if (new_character._class === "Cleric") {
      new_character.wisdom = Math.floor((new_character.wisdom = roll_dice(4)));
      new_character.wisdom += Math.floor(new_character.level / 4) + 1;
      new_character.constitution += Math.floor(new_character.level / 5) + 1;
      new_character.hitDice = "1d6";
      new_character.hitPoints = hit_dice(new_character.level);
    }
    if (new_character._class === "Bard") {
      new_character.wisdom = Math.floor((new_character.charisma = hit_dice(4)));
      new_character.wisdom += Math.floor(new_character.level / 5) + 1;
      new_character.charisma += Math.floor(new_character.level / 4) + 1;
      new_character.hitDice = "1d6";
      new_character.hitPoints = hit_dice(new_character.level);
    }
    if (new_character._class === "Druid") {
      new_character.wisdom = Math.floor((new_character.wisdom = roll_dice(4)));
      new_character.wisdom += Math.floor(new_character.level / 4) + 1;
      new_character.constitution += Math.floor(new_character.level / 5) + 1;
      new_character.hitDice = "1d6";
      new_character.hitPoints = hit_dice(new_character.level);
    }
    if (new_character._class === "Palladin") {
      new_character.strength = Math.floor(
        (new_character.strength = roll_dice(3) + 3)
      );
      new_character.strength += Math.floor(new_character.level / 5) + 1;
      new_character.constitution += Math.floor(new_character.level / 5) + 1;
      new_character.wisdom += Math.floor(new_character.level / 6) + 1;
      new_character.hitDice = "2d4";
      new_character.hitPoints = hit_dice4(new_character.level) * 2;
    }
    if (new_character._class === "Barbarian") {
      new_character.strength = Math.floor(
        (new_character.strength = roll_dice(3))
      );
      new_character.intelligence = Math.floor(
        (new_character.intelligence = roll_dice(2) + 2)
      );
      new_character.strength += Math.floor(new_character.level / 5) + 1;
      new_character.constitution += Math.floor(new_character.level / 5) + 1;
      new_character.hitDice = "2d6";
      new_character.hitPoints = hit_dice12(new_character.level) * 2;
      // new_character.hpBonus = new_character.hpBonus * new_character.level;
      // new_character.hitPoints = new_character.hitPoints + new_character.hpBonus;
    }

    //strength adjestors
    new_character.strBonus = get_str_bonus(new_character.strength); //
    new_character.liftBend =
      new_character.strength * 20 + new_character.strBonus * 100;
    new_character.carryWeight = new_character.liftBend / 4;

    //dex adjustors
    new_character.dexBonus = get_dex_bonus(new_character.dexterity); //
    new_character.dodge = new_character.dodge - new_character.dexBonus;
    new_character.speed = new_character.speed + new_character.dexBonus * 2;
    new_character.armorClass = 11 + new_character.dexBonus;
    new_character.thaco = 11 - new_character.dexBonus;
    new_character.initiative = new_character.dexBonus;

    console.log(new_character.hitPoints);
    //con adjustors
    new_character.conBonus = get_con_bonus(new_character.constitution); //
    new_character.conBonusTotal = new_character.conBonus * new_character.level;
    new_character.hitPoints =
      new_character.hitPoints + new_character.conBonusTotal;
    new_character.saveThrow = new_character.saveThrow - new_character.conBonus;

    //int adjusors
    new_character.intBonus = get_int_bonus(new_character.intelligence); //
    new_character.investigation =
      new_character.investigation - new_character.intBonus;
    new_character.skillBonus = new_character.intBonus;

    //wis adjustors
    new_character.wisBonus = get_wis_bonus(new_character.wisdom); //
    new_character.perception =
      new_character.perception - new_character.wisBonus;
    new_character.insight = new_character.insight - new_character.wisBonus;

    //cha adjustors
    new_character.chaBonus = get_cha_bonus(new_character.charisma); //
    new_character.charm = new_character.charm - new_character.chaBonus;
    new_character.intimidate =
      new_character.intimidate - new_character.chaBonus;
    //Other adjustors

    console.log(new_character.hitPoints);
    console.log(new_character.conBonus);

    return new_character;
  });
  return bonus_choices;
};

// const hit_points = () => {
//   return Math.floor(Math.random() * 6) * get_random_level;
// };

export default get_character_choices;
