import { app } from "@/app";
import { env } from "@/infrastructure/config/env";

app.listen(env.PORT, "0.0.0.0", () => {
  console.log(`Server running on port ${env.PORT}`);
});
