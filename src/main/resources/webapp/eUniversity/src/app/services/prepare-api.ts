import { environment } from "src/environments/environment"

export class PrepareApi{
  static prepare(controllerName: string, methodName: string): string{
    return environment.rootApi + '/' + controllerName + '/' + methodName;
  }
}
