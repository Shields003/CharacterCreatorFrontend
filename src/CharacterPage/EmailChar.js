import { FormControlLabel } from "@mui/material";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import { EmailOutlined } from "@mui/icons-material";
import { IconButton } from "@mui/material";

<FormControlLabel
  control={
    <a target="_top" rel="noopener noreferrer" href="mailto:test@example.com">
      <IconButton color="primary">
        <EmailOutlined /> {/* icon */}
      </IconButton>
    </a>
  }
  label={"test@example.com"}
  labelPlacement="end"
/>;
