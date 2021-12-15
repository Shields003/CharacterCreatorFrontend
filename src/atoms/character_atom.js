import { atom } from "recoil";
const characterAtom = atom({
  key: "characterAtom", // unique ID (with respect to other atoms/selectors)
  default: {}, // default value (aka initial value)
});

export default characterAtom;
