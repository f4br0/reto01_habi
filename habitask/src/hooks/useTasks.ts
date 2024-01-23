import { useQuery } from "react-query"
import { Task, TaskResult } from "../type"
import { getTask } from "../services/getTask"
import { Task } from "@mui/icons-material"

export const useTask = (page = 0, item = 10, token = '', setRows: (a: Task[]) => void) => {
  const { data: tasks = { tasks: [] }, refetch: refetchTask } = useQuery<TaskResult>(['tasks'], async () => {
    const tasks = await getTask(page, item, token)
    setRows(tasks.tasks)
    return Task;
  },

    { refetchOnWindowFocus: false, staleTime: 1000 * 60 * 5, retry: 0 })
  return { tasks, refetchTask }
}