import { TurboModule, TurboModuleRegistry } from "react-native";

/**
 * Spec for the AppRater native module
 * This interface defines the contract between JavaScript and native code
 */
export interface Spec extends TurboModule {
  /**
   * Requests an app review dialog to be shown
   * Note: The dialog may not appear if it has been shown too recently
   */
  requestReview(): void;
}

export default TurboModuleRegistry.getEnforcing<Spec>("AppRater");
