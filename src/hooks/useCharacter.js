import { useState } from "react";
import { useRecoilState } from "recoil";
import characterAtom from "../atoms/character_atom";

const useCharacter = () => {
  const [character, set_character] = useRecoilState(characterAtom);

  return {
    character,
    set_character,
  };
};



export default useCharacter;
